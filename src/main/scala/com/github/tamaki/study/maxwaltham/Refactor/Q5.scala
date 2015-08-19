package com.github.tamaki.study.maxwaltham.Refactor

/**
 * Noneは無視していい（必須ではない）
 * 空文字は許されない（１文字以上必要）
 * 数値の場合は数値文字列でないと許されない
 */
object Q5 {

  def main(args: Array[String]): Unit = {
    q5_bad
    q5_good
  }

  val x1: String = "abc"
  val x2: String = ""
  val x3: Option[String] = Some("abc")
  val x4: Option[String] = Some("")
  val x5: Option[String] = Some("12345")
  val x6: Option[String] = Some("not a number")


  def q5_strTest(name: String, str: Any): Option[(String, String)] = str match {
    case "" => Some(name -> "error.required")
    case x: String => None
    case None => Some(name -> "error.required")
    case Some(x: String) => q5_strTest(name, x)
  }

  def q5_numStrTest(name: String, optStr: Option[String]): Option[(String, String)] = optStr match {
    case None => Some(name -> "error.number")
    case Some(numStr) => try {
      numStr.toInt
      None
    } catch {
      case e: Exception =>
        Some(name -> "error.number")
    }
  }

  def q5_good = {

    q5_strTest("x1", x1) foreach println
    q5_strTest("x2", x2) foreach println
    q5_strTest("x3", x3) foreach println
    q5_strTest("x4", x4) foreach println
    q5_numStrTest("x5", x5) foreach println
    q5_numStrTest("x6", x6) foreach println

  }

  def q5_bad = {

    val errors = new scala.collection.mutable.HashMap[String, String]()

    if (x1.isEmpty) {
      errors.put("x1", "error.required")
    }
    if (x2.isEmpty) {
      errors.put("x2", "error.required")
    }
    if (x3.isDefined && !x3.get.isEmpty) {
      errors.put("x3", "error.required")
    }
    if (x4.isDefined && !x4.get.isEmpty) {
      errors.put("x4", "error.required")
    }
    if (x5.isDefined && !x5.get.isEmpty) {
      try {
        x5.get.toInt
      } catch {
        case e: Exception =>
          errors.put("x5", "error.number")
      }
    }
    if (x6.isDefined && !x6.get.isEmpty) {
      try {
        x6.get.toInt
      } catch {
        case e: Exception =>
          errors.put("x6", "error.number")
      }
    }
    println("errors = " + errors)
  }
}
