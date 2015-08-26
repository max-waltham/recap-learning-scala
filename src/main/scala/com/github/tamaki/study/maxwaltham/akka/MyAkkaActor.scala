package akka

import java.util.concurrent.Executors

import akka.actor.{Props, ActorSystem, Actor, ActorLogging}
import akka.routing.BalancingPool
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.duration._

import scala.util.{Failure, Success}


object ActorTest extends App {

  val myAkkaActorSystem = ActorSystem(name = "serverName")
  val myAkkaActor = myAkkaActorSystem.actorOf(Props(classOf[MyAkkaActor]), name = "my_akka_actor")

  implicit val executecontext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

  class MyAkkaActor extends Actor with ActorLogging {
    override def receive = {
      case i: Int => log.info("Int=" + i)
      case msg: String => {
        Thread.sleep(10L)
        log.info(msg + " by MyAkkaActor")
        sender ! s"Hello, ${msg.toString}" // )(context.parent)
      }
    }
  }

  // myAkkaActor ! "setr"
  // myAkkaActor ! 10
  //Thread.sleep(10000)

  implicit val timeout = Timeout(30 seconds)

  val resultOption = myAkkaActor ? "taro"
  resultOption.onComplete {
    case Success(s) =>
      println(s"result=$s")
    case Failure(ex) =>
      println(s"onComplete failure $ex")
  }

  myAkkaActorSystem.scheduler.schedule(5 seconds, 1 seconds, myAkkaActor, 1)
  // 1秒毎にメッセージセンド、
  // Ex…処理に５秒かかる場合もセンドされるが、同時実行数が１つなので 待たされる
  // メッセージがセンドされたが、処理されなかったやつは「デッドレター」という

  myAkkaActorSystem.scheduler.scheduleOnce(5 seconds) {
    myAkkaActor ! 3
    println("test")
  }
  myAkkaActorSystem.scheduler.schedule(5 seconds, 3 seconds) {
    myAkkaActor ! 6
  }

  class MyActorCallActor extends Actor with ActorLogging {
    implicit val executecontext = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

    implicit val timeout = Timeout(5 seconds)

    override def receive = {
      case _ =>
        Thread.sleep(10L)
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

  myActorCallActor ! "test"

  (1 to 10).foreach { x =>
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

  val myActorCallActor2 = myAkkaActorSystem.actorOf(Props(classOf[MyActorCallActor]).withRouter(BalancingPool(5)),
    name = "MyActorCallActor2")


}