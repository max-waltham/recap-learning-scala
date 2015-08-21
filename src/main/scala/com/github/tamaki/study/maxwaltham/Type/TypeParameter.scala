package com.github.tamaki.study.maxwaltham.Type

/**
 *
 */
object TypeParameter {

  def func[A](arg: A): A = {
    println(arg)
    arg
  }

  def func2[A, B](a: A)(f: A => B): B = {
    println(a)
    f(a)
  }

  // Object > java.util.Date > java.sql.Date > ..TimeStamp 
  // java.util.Dateを継承したものに限定
  def func3[A <: java.util.Date](a: A) = println(a)

  // java.sql.Date の上位の親クラスに限定（ObjectもOkになる）
  def func4[A >: java.sql.Date](a: A) = println(a)

  // 
  //def func5[A >: java.util.Date <: java.sql.Date](a: A) = println(a)

  def func6[A <: {def shout() : Unit}](a: A) = a.shout()

  case class Cat() {
    def shout() = println("nyaa")
  }

  class Dog() {
    def shout() = println("waan")
  }

  def main(args: Array[String]): Unit = {
    func("a")
    val b = func2(123)(Integer.toString)
    println(b)
    func6(new Cat)
    func6(new Dog)
  }

  def fly[A](a: A) = a

  def aa[A](implicit ev: A <:< CharSequence) = println(ev)

  //class B [({type R[A] = List[A]})#R] () {

  //}
}
