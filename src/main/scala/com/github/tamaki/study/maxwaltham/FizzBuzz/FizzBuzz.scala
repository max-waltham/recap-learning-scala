package com.github.tamaki.study.kota.saito.FizzBuzz

/**
 * Created by tamaki on 2015/02/08.
 */
object FizzBuzz {
  def main(args: Array[String]): Unit = {
    // 1-100
    // 3 Fizz
    // 5 Buzz
    // 15 FizzBuzz
    (1 to 100).foreach({
      case y if y % 3 == 0 && y % 5 == 0 => println("FizzBuzz")
      case x if x % 3 == 0 => println("Fizz")
      case x if x % 5 == 0 => println("Buzz")
      case x => println(x)
    })


    val fb = (1 to 100).map(x => (x%3, x%5) match{
      case (0,0) => "FizzBuzz"
      case (0,_) => "Fizz"
      case (_, 0) => "Buzz"
      case _ => x.toString
    }) foreach println
  }
}
