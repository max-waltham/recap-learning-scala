package com.github.tamaki.study.maxwaltham.Refactor

/**
 * Created by kota.saito on 2015/08/19.
 */
class Q {

  def findByName(name: String) = ""

  def q1(nameOpt: Option[String]) = {
    // bad
    if(nameOpt.isDefined){
      val name = nameOpt.get
      val id = findByName(name)
    }
    // good?
    findByName(nameOpt.getOrElse(""))

  }
}
