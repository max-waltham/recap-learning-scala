package com.github.tamaki.study.maxwaltham.function

/**
 * Created by kota.saito on 2015/08/12.
 */
object Fibonacci {

  def fib(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fib(n - 2) + fib(n - 1)
  }

  def main(args: Array[String]) = {
    println(fib(2))
    println(fib(7))
  }
}
