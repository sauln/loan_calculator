package loan


/** 
 * Do I write it first, and then make it functional?
 * Or do I write it functional from the get go?  
 */


/**
 * end the end, we want a list of (loan, payment) tuples so we can show a table
 * of all the values that we come up with.
 *
 */


//want loans, payments  - should there be an object that is (loan, payment)??

object Strategy{

  type Strat =  (List[Loan], Double) => List[(Double, Loan)]

  def minimum: Strat = {
    case (loans: List[Loan], b: Double) =>
 // def minimum(loans: List[Loan], available_payment: Double): List[(Double, Loan)] = {
       loans.map(_.min) zip loans
  }
  def even(loans: List[Loan], available_payment: Double): List[(Double, Loan)] = {
      ((loans.map(_.min) zip
      List.fill(loans.length)(available_payment/loans.length))
      .map{ case(a,b) => a max b }) zip loans
  }
  def snowball(loans: List[Loan], available_payment: Double): List[(Double, Loan)] = {
      loans.map(_.min) zip loans
  }
}






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
      val current_Loan = Student(interest, balance - interest_paid, min)
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




