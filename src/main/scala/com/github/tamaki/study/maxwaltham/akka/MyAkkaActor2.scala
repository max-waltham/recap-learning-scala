package com.github.tamaki.study.maxwaltham.akka

import java.util.concurrent.Executors

import akka.actor.{Props, ActorSystem, Actor, ActorLogging}
import akka.routing.BalancingPool
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.duration._

//import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


object ActorTest2 extends App {

  val myAkkaActorSystem = ActorSystem(name = "serverName")
  val myAkkaActor = myAkkaActorSystem.actorOf(Props(classOf[MyAkkaActor]), name = "my_akka_actor")


  class MyAkkaActor extends Actor with ActorLogging {
    implicit val executeContext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

    override def receive = {
      case i: Int => log.info("Int=" + i)
      case msg: String => {
        Thread.sleep(10L)
        log.info(msg + " by MyAkkaActor")
        sender ! s"Hello, ${msg.toString}"
      }
    }
  }

  implicit val timeout = Timeout(30 seconds)

  class MyActorCallActor extends Actor with ActorLogging {
    implicit val executeContext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

    implicit val timeout = Timeout(5 seconds)
    override def receive = {
      case _ =>
        Thread.sleep(3L)
        val resultFuture = context.actorOf(Props(classOf[MyAkkaActor])) ? "MyActorCallActor"
        resultFuture.onComplete {
          case Success(s) =>
            log.info(s"MyActorCallActor onComplete result=$s")
            sender ! s"success MyActorCallActor onComplete result=$s"
          case Failure(ex) =>
            log.debug(s"MyActorCallActor onComplete failure $ex")
            sender ! s"failure MyActorCallActor onComplete failure=$ex"
        }
    }
  }

  val myActorCallActor = myAkkaActorSystem.actorOf(Props(classOf[MyActorCallActor]),
    name = "MyActorCallActor")

  (1 to 10).foreach { x =>
    implicit val executeContext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

    // 10個一気にセンドすると良くない
    val resultFuture = myActorCallActor ? "myActorCallActor takeshi"
    resultFuture.onComplete {
      case Success(s) =>
        println(s"$x myActorCallActor onComplete result=$s")
      case Failure(ex) =>
        println(s"$x myActorCallActor onComplete failure $ex")
    }
  }

  //ロードバランサー的な仕組み いろいろな振り分け方が指定できる

  val myActorCallActor2 = myAkkaActorSystem.actorOf(
    Props(classOf[MyActorCallActor]).withRouter(BalancingPool(10)),
    name = "MyActorCallActor2")

  (11 to 20).foreach { x =>
    implicit val executeContext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

    // 10個一気にセンドすると良くない
    val resultFuture = myActorCallActor2 ? "myActorCallActor takeshi"
    resultFuture.onComplete {
      case Success(s) =>
        println(s"$x myActorCallActor onComplete result=$s")
      case Failure(ex) =>
        println(s"$x myActorCallActor onComplete failure $ex")
    }
  }
}