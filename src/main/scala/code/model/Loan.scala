package code
package model


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

import net.liftweb.common.Full
import net.liftweb.http.S
import net.liftweb.util.PassThru
import scala.xml.{NodeSeq, Text, XML}


/**
 * This class represents the model of the loans
 */

/**
 * How do I capture state in a functional way?
 */

class Loan (guid: Int, balance: Double, interest: Double, minimum: Double) {


	var compound_var = 12


  def sum: Double = 
    balance + interest + minimum

  def putXML: NodeSeq = {
    <div>
      <string> Balance: {balance} Interest: {interest} Minimum: {minimum} </string>
      <br/>
    </div>
  }

  override def toString =  
    "Balance: %s  Interest: %s  Minimum: %s".format(balance, interest, minimum)

}




/* rename Loans to portfolio */
object Loans {

  var count = 1
  var loans =  List[Loan]() 

  def SOMEVALUE = 12345


	def sum = { loans.map(_.sum).foldLeft(0.0)(_+_) }


  def show_sum = {
    <div>
    	<string> {sum.toString} </string>
    </div>
  }

  def show = { 
    loans.flatMap(_.putXML)
  }

  def add(b: String, i: String, m: String): Unit = {
    var tmpLoan = new Loan(count, b.toDouble, i.toDouble, m.toDouble)
    loans =  tmpLoan :: loans
    count += count  
  }

}


