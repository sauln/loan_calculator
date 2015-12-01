package code
package snippet

import net.liftweb._
import http._

/**
 *  Declare the fields on the screen
 */


object ScreenExample extends LiftScreen {
  val balance = field("balance", 0, minVal(0, "Already paid off!"))
  val interest = field("interest rate", 0, minVal(0, "So you're making money?"))
  val minimum_payment = field("minimum payment", 0, minVal(0, "probably not what you meant"))



  def finish() {





    S.notice("Balance: " +balance)
    S.notice("Interest_rate: "+interest)
    S.notice("Minimum payment: "+minimum_payment)
  }
}
