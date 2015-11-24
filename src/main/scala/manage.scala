/** 
 * this will be the source of the project and my testing
 * 
 */









import loan.loan



object run{
  def main(args: Array[String]) {
    println("hello from manager!")
    val loan1 = new loan(1.2, 233.4, 23)
    val loan2 = new loan(0.8, 10023, 75)


    println(loan1.current_balance)
    loan1.pay(5)
    
    println(loan1.current_balance)
  }
}




















