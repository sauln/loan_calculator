/** 
 * this will be the source of the project and my testing
 * eventually incorporate more structured testing
 */



/**
 * want:  
 *  create a handful of Loans
 *  send stack of Loans to optimization algorithm
 *  return is the best pay given the strategy
 */







import loan.Loan
import loan.Strategy



object run{

//  def make_pay(loan: Loan, payment: Double): Loan = 
//    { loan pay payment }

  //def make_payments(loans: List[Loan], strat: Strat,  



  def main(args: Array[String]) {
    println("hello from manager!")
    val loan1 = new Loan(1.2, 233.4, 23)
    val loan2 = new Loan(0.8, 10023, 75)
    val loan3 = new Loan(.5, 3223, 123)

    val loans = loan1::loan2::loan3::Nil





    // I want there to be one operation of making payments,
    // but I don't want the strategy to know any more about payments
    // then it has to...
    // strategy should read the loan attributes, but not be able to put
    // send in the function Strategy.minimum as an argument to pay
    // that would look like:
    // pay(loans, Strategy.even, 400)

    val pay_min = Strategy.minimum(loans, 0) 
    val pay_eq  = Strategy.even(loans, 400)

    val new_loans_wmin = pay_min.map{case(a,b) => b make_payment a}
    val new_weq        = pay_eq.map{case(a,b) => b make_payment a}



    println(loans.map(_.balance))
    println(new_loans_wmin.map(_.balance))
    println(new_weq.map(_.balance))

    






  }
}




















