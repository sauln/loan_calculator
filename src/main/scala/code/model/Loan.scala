package code
package model




/**
 * This class represents the model of the loans
 *
 */

/**
 * How do I capture state in a functional way?
 *
 */





object Loan {
  var balance = ""
  var interest = ""
  var minimum = ""


  def add(b: String, i: String, m: String): Unit = {
    balance = b
    interest = i
    minimum = m
  }

  def sum = (balance.toDouble + interest.toDouble + minimum.toDouble).toString

  def show = "Balance: %s  Interest: %s  Minimum: %s".format(balance, interest, minimum)

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




