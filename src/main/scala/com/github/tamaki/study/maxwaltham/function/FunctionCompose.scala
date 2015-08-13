package com.github.tamaki.study.maxwaltham.function

/**
 * Created by kota.saito on 2015/08/13.
 */
object FunctionCompose {

  def main(args: Array[String]) = {
    val f = (x: Int) => x + 1
    val g = (x: Int) => x * 3

    val h = (x: Int) => f(g(x))
    val h2 = f.compose(g)
    val h3 = f compose g
    // f andThen g

    println(h)
    println(h(3))

    println(h2)
    println(h2(3))

    println(h3)
    println(h3(3))
  }
}
