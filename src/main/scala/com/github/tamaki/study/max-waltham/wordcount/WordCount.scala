package com.github.tamaki.study.kota.saito.wordcount

/**
 * Created by tamaki on 2015/02/08.
 */


class WordCount {

  //------------------------------------------------------
  // ワードカウント問題
  // https://gist.github.com/j5ik2o/7210762
  //------------------------------------------------------
  def countFruitsFromLines(lines: List[String]): Map[String, Int] = {
    val a = lines.flatMap(s => s.split(" "))
    println(a)
    null
  }

  // リスト内の文字列を空白で区切り、新しいリストを作る。
  // リスト内のワードを見て行って何が何回出たのか数えて、マップを作る。
  val lines = List("apple banana", "orange apple mango", "kiwi papaya orange","mango orange muscat apple")
  val fruitsCounts = countFruitsFromLines(lines)
  if (fruitsCounts == Map("banana" -> 1, "muscat" -> 1, "orange" -> 3, "mango" -> 2, "apple" -> 3, "kiwi" -> 1, "papaya" -> 1)){
    println("正解")
  } else {
    println("不正解")
  }
//  /**
//   * 勉強会をやる発端となったダメコード
//   * @param lines
//   * @return
//   */
//  def countFruitsFromLines(lines: List[String]): Map[String, Int] = {
//    lines.foldLeft(new scala.collection.mutable.HashMap[String, Int]) { (b, line) =>
//      line.split(" ").map( m => {
//        val cnt:Int = b.get(m).getOrElse(0) + 1
//        b.put(m, cnt)
//      })
//      b
//    }.toMap
//  }

}
