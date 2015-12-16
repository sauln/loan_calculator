package com.pocketchangeapp.model




import net.liftweb.common._
import net.liftweb._
import http._
import SHtml._
import util._
import Helpers._
import js._
import js.JsCmds._
import js.jquery._
import js.jquery.JqJsCmds._

import scala.xml.{NodeSeq, Text}


import java.math.MathContext
import net.liftweb.mapper._
//import net.liftweb.util.Empty

class Account extends LongKeyedMapper[Account] with IdPK {
  def getSingleton = Account

  //many-to-one relationship to the user class

  //
  object is_public extends MappedBoolean(this) {
    override def defaultValue = false
  }

  object balance extends MappedDecimal(this, MathContext.DECIMAL64, 2)

  object name extends MappedString(this, 100)
  object description extends MappedString(this, 300)

}

object Account extends Account with LongKeyedMetaMapper[Account] {

}








