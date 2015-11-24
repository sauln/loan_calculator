package loan

/** 
 * Do I write it first, and then make it functional?
 * Or do I write it functional from the get go?  
 */



class loan(interest_rate: Double, principal: Double, minimum_payment: Double){
  var current_balance = principal
  var interest = interest_rate  

  def pay(payment: Double){ 
    current_balance -= payment 
  }

  def accrue_interest(time_since: Double){ 
    current_balance += current_balance * (1 + time_since * interest_rate)
  }




}










