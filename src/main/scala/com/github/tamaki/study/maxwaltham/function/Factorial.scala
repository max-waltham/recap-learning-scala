package com.github.tamaki.study.maxwaltham.function

import scala.annotation.tailrec

/**
 * Created by kota.saito on 2015/08/17.
 */
object Factorial {

  def factorial2(n: Int): Int = n match {
    case 0 => 1
    case m => m * factorial2(n - 1)
  }

  def factorial(n: Int): BigInt = {
    @tailrec
    def go(m: Int, acc: BigInt): BigInt = m match {
      case 1 => acc
      case l => go(l - 1, l * acc)
    }

    go(n, 1)
  }

  def main(args: Array[String]) = {
    println(factorial(10000))
  }

}
