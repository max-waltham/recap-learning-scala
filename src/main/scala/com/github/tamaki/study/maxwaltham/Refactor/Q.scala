package com.github.tamaki.study.maxwaltham.Refactor

/**
 * Created by kota.saito on 2015/08/19.
 */
object Q {

  def main(args: Array[String]) = {
    q1(Some("kota"))
    q1(None)
    q3(Some(" kota "))
    q3(Some("  "))
    q4
  }

  def findByName(name: String) = "id:" + name

  /**
   * Optionにmap
   * @param nameOpt
   */
  def q1(nameOpt: Option[String]) = {
    // bad
    if (nameOpt.isDefined) {
      val name = nameOpt.get
      val id = findByName(name)
      println(id)
    }

    // good
    println(nameOpt.map(findByName))
  }

  /**
   * Optionにmatch
   * @param nameOpt
   */
  def q2(nameOpt: Option[String]) = {
    // bad
    val a = nameOpt.isDefined match {
      case true =>
        None
      case false =>
        // do something
        Some("x")
    }

    // good
    val b = nameOpt match {
      case None =>
        ""
      case Some(x) =>
        // do something
        x
    }
  }

  /**
   * OptionにwithFilter、foreach
   * @param nameOpt
   */
  def q3(nameOpt: Option[String]) = {
    // bad
    nameOpt.foreach { name =>
      if (name.trim.nonEmpty) {
        println(name)
      }
    }

    // good
    nameOpt.withFilter(_.trim.nonEmpty).foreach {
      println(_)
    }
  }

  def q4 = {
    val values = List(1, 2, 3)

    // bad
    val r1 = values.length match {
      case 0 => 0
      case 1 =>
        val h = values.head
        println(h)
        h
      case 2 =>
        val h1 = values(0)
        val h2 = values(1)
        h1 + h2
      case 3 =>
        val h1 = values(0)
        val h3 = values(2)
        h1 + h3
    }

    // good. case文で変数にバインドして使えるように考える
    val r2 = values match {
      case Nil => 0
      case head :: Nil =>
        println("head = " + head)
        head
      case first :: second :: Nil =>
        first + second
      case first :: _ :: third :: Nil =>
        first + third
    }
  }

}
