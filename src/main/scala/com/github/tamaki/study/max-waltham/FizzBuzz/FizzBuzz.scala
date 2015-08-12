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
    val ls = (1 to 16)
    ls.map(x => x match {

      case y if y%3 == 0 && y%5 ==0 => println("FizzBuzz")
      case x if x%3 == 0 => println("Fizz")
      case x if x%5 == 0 => println("Buzz")

      case x => println(x)
    })

    // リスト内の文字列を空白で区切り、新しいリストを作る。
    // リスト内のワードを見て行って何が何回出たのか数えて、マップを作る。
    val lines = List("apple banana", "orange apple mango", "kiwi papaya orange","mango orange muscat apple")

    val a = lines.flatMap(s => s.split(" "))
    println(a)
    a.map{
      case x => ?
    }



  }
}
