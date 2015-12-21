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
 *
 */

/**
 * How do I capture state in a functional way?
 *
 */


//case class Loan(val guid: Int, val balance: Double, val interest: Double, val minimum: Double) 


class Loan (guid: Int, balance: Double, interest: Double, minimum: Double) {

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


object Loans {

  var count = 1
  var loans =  List[Loan]() 


  def SOMEVALUE = 12345

  def sum = {
    
    <div>
    <string> {loans.map(_.sum).foldLeft(0.0)(_+_).toString} </string>
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



/*
class Loan(i: Double, b: Double, m: Double){
  val min = m
  val balance = b
  val interest = i

  def make_payment(payment: Double): Loan = {
    val new_balance = balance - payment
    val current_Loan = new Loan(interest, new_balance, min)
    current_Loan
  }
  def accrue_interest(months: Int = 1): Loan = {
    val interest_paid = balance * ( 1 + (interest * months)/12)
    val current_Loan = new Loan(interest, balance - interest_paid, min)
    current_Loan
  }
}
*/

































/*
trait Loan{
  def make_payment(payment: Double): Loan
  def accrue_interest(months: Int): Loan
}
*/
/*
object Loan {
  case class Student(interest: Double, balance: Double, min: Double)
    extends Loan {
    def make_payment(payment: Double): Loan = {
      val new_balance = balance - payment
      val current_Loan = Student(interest, new_balance, min)
      current_Loan
    }
    def accrue_interest(months: Int = 1): Loan = {
      val interest_paid = balance * ( 1 + (interest * months)/12)
      dval current_Loan = Student(interest, balance - interest_paid, min)
      current_Loan
    }
  }

}
*/






//class Loan(interest_rate: Double, principal: Double, min: Double){
  //var current_balance = principal
  //var interest = interest_rate 
  //val minimum_payment = min

  //I think this is a bad way to do it.
  //def pay(payment: Double): Loan =  { 
  //  current_balance -= payment
  //  this
  //}

  //def accrue_interest(time_since: Double) { 
  //  current_balance += current_balance * (1 + time_since * interest_rate)
  //}





//object Loan{

  //def pay_new(ln: Loan, payment: Double): Loan = {
  //  val a = new Loan(ln.interest, ln.current_balance - payment, ln.minimum_payment)
  //  a
  //}


//}




