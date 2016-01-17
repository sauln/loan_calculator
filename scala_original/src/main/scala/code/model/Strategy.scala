package code
package model


//import Loan._
/**
 * This object captures the idea of various repayment strategies.
 * Will probably be refactored many more times.
 */



/* 
object Strategy{

  type Strat =  (List[Loan.Loan], Double) => List[(Double, Loan.Loan)]
  
  // Pay just the minimum on each loan
  def minimum: Strat = {
    case (loans: List[Loan], b: Double) =>
   // def minimum(loans: List[Loan], available_payment: Double): List[(Double, Loan)] = {
       loans.map(_.min) zip loans
  }


  // Pay evenly across all loans, or minimum, whichever is more
  def even(loans: List[Loan], available_payment: Double): List[(Double, Loan)] = {
      ((loans.map(_.min) zip
      List.fill(loans.length)(available_payment/loans.length))
      .map{ case(a,b) => a max b }) zip loans
  }

  // Pay off the loan with smallest balance first
  def snowball(loans: List[Loan], available_payment: Double): List[(Double, Loan)] = {
      loans.map(_.min) zip loans
  }
}

*/


