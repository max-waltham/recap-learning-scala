package com.github.tamaki.study.kota.saito.wordcount

/**
 * Created by tamaki on 2015/02/08.
 */


object WordCount {

  //------------------------------------------------------
  // ワードカウント問題
  // https://gist.github.com/j5ik2o/7210762
  //------------------------------------------------------
  def countFruitsFromLines_(lines: List[String]): Map[String, Int] = {
    val a = lines.flatMap(_.split(" "))
    println(a)

    //////
    def go(c: Int, l: List[String], m: Map[String, Int]): Map[String, Int] = {
      if (c == 0)
        m
      else {
        val k = m.get(l.head).getOrElse(0)
        val newM = m + (l.head -> (k + 1))
        go(c - 1, l.tail, newM)
      }
    }
    go(a.length, a, Map.empty)
    //////

    a.foldLeft(Map.empty[String, Int])((mp, str) => mp + (str -> (mp.get(str).getOrElse(0) + 1)))

  }

  def countFruitsFromLines(lines: List[String]): Map[String, Int] =
    lines.flatMap(_.split(" ")).foldLeft(Map.empty[String, Int]) {
      (mp, str) =>
        mp + (str -> (mp.get(str).getOrElse(0) + 1))
    }

  def main(args: Array[String]): Unit = {
    // リスト内の文字列を空白で区切り、新しいリストを作る。
    // リスト内のワードを見て行って何が何回出たのか数えて、マップを作る。
    val lines = List("apple banana", "orange apple mango", "kiwi papaya orange", "mango orange muscat apple")
    val fruitsCounts = countFruitsFromLines(lines)
    println(fruitsCounts)
    if (fruitsCounts == Map("banana" -> 1, "muscat" -> 1, "orange" -> 3, "mango" -> 2, "apple" -> 3, "kiwi" -> 1, "papaya" -> 1)) {
      println("正解")
    } else {
      println("不正解")
    }
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
