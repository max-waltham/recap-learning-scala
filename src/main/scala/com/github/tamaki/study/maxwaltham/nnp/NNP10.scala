package com.github.tamaki.study.kota.saito.nnp

/**
 * Created by tamaki on 2015/02/08.
 */
trait NNP10 {

  // P01 (*) Find the last element of a list.
  def last(list: List[Int]): Int =
    list match {
      case head :: Nil => head
      case head :: tail => last(tail)
    }

  last(List(1, 2, 10))

  // P02 (*) Find the last but one element of a list.
  def penultimate(list: List[Int]): Int = list match {
    case head :: last :: Nil => head
    case head :: tail => penultimate(tail)
    case _ => 0
  }

  penultimate(List(1, 2, 3, 10))

  def nth(n: Int, list: List[Int]): Int = {
    def go(n: Int, list: List[Int]): Int =
      (n, list) match {
        case (0, head :: tail) => head
        case (_, head :: tail) => go(n - 1, tail)
      }
    go(n, list)
  }

  nth(2, List(1, 1, 2, 3, 5, 8))

  def length(list: List[Int]): Int = {
    def go(list: List[Int], acc: Int): Int = list match {
      case Nil => acc
      case head :: tail => go(tail, acc + 1)
    }
    go(list, 0)
  }

  length(List(1, 1, 2, 3, 5, 8))

  def reverse(list: List[Int]): List[Int] = {
    def go(list: List[Int], acc: List[Int]): List[Int] = list match {
      case Nil => acc
      case head :: tail => go(tail, head :: acc)
    }
    go(list, List.empty)
  }

  reverse(List(1, 1, 2, 3, 5, 8))

  def isPalindrome(list: List[Int]): Boolean = {
    ???
  }

  def flatten(nested: List[Any]): List[Any] = {
    ???
  }

  def compress(list: List[Symbol]): List[Symbol] = {
    ???
  }

  def pack(list: List[Symbol]): List[List[Symbol]] = {
    ???
  }

  def encode(list: List[Symbol]): List[(Int, Symbol)] = {
    ???
  }

}