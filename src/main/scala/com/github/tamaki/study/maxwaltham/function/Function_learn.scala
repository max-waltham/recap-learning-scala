package com.github.tamaki.study.maxwaltham.function


/**
 * Created by kota.saito on 2015/08/12.
 */

object Function_learn {

  def createAdder(arg: Int): Function1[Int, Int] = {
    def i(x: Int): Int = {
      x + arg
    }
    i
  }

  def area(pi: Double) = (r: Double) => r * r * pi

  def main(args: Array[String]) = {
    val addr3 = createAdder(3)
    println(addr3(5))

    val addr5 = createAdder(5)
    println(addr5(4))

    val f1 = area(3.14)
    println(f1(10))

    val f2 = area(3)
    println(f2(10))

  }

  def ca(x: Int) = (y: Int) => x + y

  def adder(x: Int, y: Int) = x + y
  //val adder3_ = adder(3, _)

}
