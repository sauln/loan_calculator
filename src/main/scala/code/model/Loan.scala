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

class Loan(guid: Int, bal: Double, interest_rate: Double, minimum: Double) {


	var compound_var = 12


	def balance = bal

	def interest = balance * (interest_rate/compound_var)

  def putXML: NodeSeq = {
    <div>
      <string> Balance: {balance} Interest rate: {interest_rate} Minimum: {minimum} </string>
      <br/>
    </div>
  }

  override def toString =  
    "Balance: %s  Interest rate: %s  Minimum: %s".format(balance, interest_rate, minimum)

}




/* rename Loans to portfolio */
object Loans {

  var count = 1
  var loans =  List[Loan]() 

  def SOMEVALUE = 12345


	def sum = { loans.map(_.balance).foldLeft(0.0)(_+_) }


  def show_sum = {
    <div>
    	<string> Total debt: {sum.toString} </string><br/>
			<string> Interest: {loans.map(_.interest).foldLeft(0.0)(_+_).toString } </string>
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


