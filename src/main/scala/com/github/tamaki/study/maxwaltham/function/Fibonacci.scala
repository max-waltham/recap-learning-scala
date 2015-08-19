package com.github.tamaki.study.maxwaltham.function

import scala.annotation.tailrec

/**
 * Created by kota.saito on 2015/08/12.
 */
object Fibonacci {

  def fib_2(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fib_2(n - 2) + fib_2(n - 1)
  }

  @tailrec
  def fib(n: Int, a: Int = 0, b: Int = 1): Int = n match {
    case 0 => 0
    case 1 => b
    case n => fib(n - 1, b, b + a)
    //c-1, f(0), f(1)     // = 1
    //c-2, f(1), f(2)     // = f(0) + f(1)
    //c-3, f(2), f(3)     // = f(1) + f(2)
    //c-4, f(3), f(4)     // = f(2) + f(3)
    //c-5, f(4), f(5)     // = f(3) + f(4)
  }

  def main(args: Array[String]) = {
    //0  1  2  3  4  5  6  7   8
    //0, 1, 1, 2, 3, 5, 8, 13, 21

    println(fib(0))
    println(fib(1))
    println(fib(2))
    println(fib(3))
    println(fib(4))
    println(fib(5))
    println(fib(6))
    println(fib(7))
    println(fib(8))
    println(fib(9))
  }
}
