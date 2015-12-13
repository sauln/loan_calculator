package code
package snippet



/**
 * This page is an mostly a scratch pad for different loan input examples 
 */

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

import scala.xml.{NodeSeq, Text}

/**
 * Most of this code has been found from the demo or simple lift sights
 * I'm having difficulty with old versions and finding examples from old versions
 * also, it seems much of what I have to learn is actually jQuery, not Scala.
 */


/**
 * WANT:  An input line for a loan
 *        when one loan is input, a new line appears
 *        loans are progressivly added to a list of loans
 *        when "calculate" button is pressed, the chosen strategy is picked up
 *        the payoff results for the loans and given strategy is calculated and displayed.
 */




/**
 * I think that wiring is unnecessary - the example was good, but I want to be able to 
 * add a new line via ajax without the page refreshing everything a field is updated.
 * I believe I have to dig deeper into JavaScript to do this.  
 * 
 * Idea - when button is pushed, pull data into an object that is maintained per user
 *        also generate new line of input data and turn input fields where loan was input 
 *        into display values.
 *        
 */



case class Loan(guid: String, 
                name: String, 
                balance: Double, 
                interest_rate: Double, 
                minimum: Double)


object LoanIn {
  case class LoanItem(balance: String, minpayment: String, interest: String)
  def render = {
    var balance = ""
    var minimum = ""
    var interest = ""

    val whence = S.referer openOr "/"


    



    def process(): JsCmd = {
      
      S.notice("Balance: "+balance)
      S.notice("Interest: "+interest)

      Thread.sleep(500)        
    }
    

    //"name=balance" #> SHtml.onSubmit(balance= _ ) &
    //"name=minpayment" #> SHtml.onSubmit(minpayment= _ ) &
    //"name=interest" #> SHtml.onSubmit(interest= _ )

    "name=minimum" #> SHtml.text(minimum, minimum = _) &
    "name=balance" #> SHtml.text(balance, balance = _ ) &
    "name=interest" #> (SHtml.text(interest, interest = _) ++ SHtml.hidden(process))
  }
}




class Ajax extends Loggable {


  def render = {
    var cnt = 0


    //add a loan entry

    


    def doClicker(in: NodeSeq) = a(() => {
      cnt = cnt + 1;
      SetHtml("count", Text(cnt.toString))
    }, in)


    def doSelect(in: NodeSeq) = ajaxSelect(
      List("dog", "cow", "pig").map(i => (i.toString, i.toString)),
      Full("dog"),
      v => DisplayMessage("messages", ("#number" #> Text(v)).apply(in), 5 seconds, 1 second))


    def doText(in: NodeSeq) = ajaxText("", v => DisplayMessage("messages", ("#value" #>
      Text(v)).apply(in), 4 seconds, 1 second))

    "#clicker" #> doClicker _ &
      "#select" #> doSelect _ &
      "#ajaxText" #> doText _

  }


  private def buildQuery(current: String, limit: Int): Seq[String] = {
    logger.info("Checking on server side with " + current + " limit " + limit)
    (1 to limit).map(n => current + "" + n)
  }

  def buttonClick = {
    import js.JE._
    "* [onclick]" #> SHtml.ajaxCall(ValById("the_input"),
    s => SetHtml("messages", <i> Text box is 
      {s}
    </i>))
  }
}




class LoanWiring {

  /**
   * define the relationships amoung items
   */
  private object Info {
    val loans = ValueCell(List(newLoan))//declares a new line


    //val loans = List(newLoan)

    //val taxRate = ValueCell(0.05d)//sets default taxRate
    //val subtotal = loans.lift(_.foldLeft(0d)(_ + _.balance))
    //val interest = loans.lift(_.foldLeft(0d)
    //               {case (a, b) => a + (b.balance * b.interest_rate)})
    //val total   = subtotal.lift(interest) {_ * _}

  }



  def calculate_values = 
    "* [onClick]" #> ajaxInvoke(() => {
      
      val balance = S.param("bal") openOr "no input"

      SetHtml("calc", Text(balance))
    })



  /**
   * draw all the input lines
   */
  def showLoans = "* *" #> (Info.loans.get.flatMap(renderLoan): NodeSeq)

  /**
   * add a line to the input
   */


  
  def addLoan = 
    "* [onclick]" #> ajaxInvoke(() =>
      JqJsCmds.AppendHtml("loan_inputs", renderLoan(appendLoan)))



  private def renderLoan(theLoan: Loan): NodeSeq = {
    import theLoan._

    <div id={guid}>
    {ajaxText(name, s => mutateLoan(guid)(_.copy(name = s)))}

    {ajaxText(balance.toString,
                (d: Double) => mutateLoan(guid) {_.copy(balance = d)})}

    {ajaxText(interest_rate.toString,
                (d: Double) => mutateLoan(guid) {_.copy(interest_rate = d)})}
    
    {ajaxText(minimum.toString,
                (d: Double) => mutateLoan(guid) {_.copy(minimum = d)})}
    
    </div>
  }

  private def newLoan = Loan(nextFuncName, "name", 0, 0,0)

  //private def cutLastLoan: Loan = {
  //  val kept= Info.loans.dropRight(1)
  //}

  private def appendLoan: Loan = {
    val ret = newLoan
    Info.loans.set(ret :: Info.loans.get)
    ret
  }

  /**
   * Mutate a line and update the info field
   */

  private def mutateLoan(guid: String)(f: Loan => Loan) {
    val all = Info.loans.get
    val head = all.filter(_.guid == guid).map(f)
    val rest = all.filter(_.guid != guid)
    Info.loans.set(head ::: rest)
  }


  // convert a Double to a NodeSeq
  private def doubleDraw: (Double, NodeSeq) => NodeSeq =
    (d, ns) => Text(java.text.NumberFormat.getCurrencyInstance.format(d))


  // "helpful implicit conversions
  private implicit def unitToJsCmd(in: Unit): JsCmd = Noop
  private implicit def doubleToJsCmd(in: Double => Any): String => JsCmd =
    str => {asDouble(str).foreach(in)}
}



