package com.github.tamaki.study.maxwaltham.function

/**
 * Created by kota.saito on 2015/08/13.
 */
object ChainFunction {

  def chainFunction(fs: (Int => Int)*): (Int => Int) =
    fs.tail.foldLeft(fs.head)((f, g) => f andThen g)

  def chainFunction2(fs: (Int => Int)*): (Int => Int) =
    fs.reduce((f, g) => f andThen g)


  def main(args: Array[String]): Unit = {
    val f1 = (x: Int) => x + 1
    val f2 = (x: Int) => x * 2
    val f3 = (x: Int) => x - 1

    val f4 = chainFunction(f1, f2, f3)
    println(f4(10))

    // reduce
    println(List(1, 2, 3).reduce((a, x) => a - x))
  }
}
