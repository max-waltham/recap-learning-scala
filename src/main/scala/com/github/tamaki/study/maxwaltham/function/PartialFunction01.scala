package com.github.tamaki.study.maxwaltham.function

/**
 * Created by kota.saito on 2015/08/14.
 */
object PartialFunction01 {
  
  def replace(seq: Seq[String], f: PartialFunction[String, String]) = {
    seq.map(s => if (f.isDefinedAt(s)) f(s) else s)
  }

  def main(args: Array[String]) = {

    val pf: PartialFunction[String, String] = {
      case "bar" => "BAR!"
    }
    println(pf("bar"))

    val pf2: PartialFunction[String, String] = {
      case "a" => "A"
    }

    val pf3: PartialFunction[String, String] = {
      case "b" => "B"
      case _ => "un match"
    }

    val func = pf orElse pf2 orElse pf3

    println(func("a"))
    println(func("asdf"))

    val langs = Seq("Java", "JavaScript", "Scala")
    val result = replace(langs, { case "Scala" => "Cool" })
    println(result)
  }
}
