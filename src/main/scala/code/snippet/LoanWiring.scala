package code
package snippet


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


// got the original for this code at seventhings.liftweb.net/wiring
// will use this as a basis for the loan input system


case class Loan(guid: String, 
                name: String, 
                balance: Double, 
                interest_rate: Double, 
                minimum: Double)
//case class Line(guid: String, name: String, price: Double, taxable: Boolean)





// want to be able to add a new line for loan inputs
// want a button click to calculate the results
//


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


    //val taxRate = ValueCell(0.05d)//sets default taxRate
    //val subtotal = loans.lift(_.foldLeft(0d)(_ + _.balance))
    //val interest = loans.lift(_.foldLeft(0d)
     //               {case (a, b) => a + (b.balance * b.interest_rate)})
    //val total   = subtotal.lift(interest) {_ * _}

  }


  // these are the calculated elements that are wired to input values
  //def subtotal = WiringUI.toNode(Info.subtotal)(doubleDraw)
  //def interest = WiringUI.toNode(Info.interest, JqWiringSupport.fade)(doubleDraw)
  //def total = WiringUI.toNode(Info.total, JqWiringSupport.fade)(doubleDraw)


  //def taxRate = ajaxText(Info.taxRate.get.toString, 
   //                      doubleToJsCmd(Info.taxRate.set))


 // def doCalculate(in: NodeSeq) = a(() => {
 //   SetHtml("count", Text("Hey Dude"))
 // }, in)


  // "#calculate_values" #> doCalculate _ 

  def calculate_values = 
    "* [onClick]" #> ajaxInvoke(() => {
      SetHtml("calc", Text("Hey dude"))
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

  //def removeLoan = 
  //  "* [onclick]" #> ajaxInvoke(() =>
  //    JqJsCmds.EmptyAfter("loan_inputs", Nil))
  /**
   * render a line of input fields
   */


/*
  private def renderCalculated(myVar: String): NodeSeq = {

    <div id="key">

    {ajaxText(myVar,
                (str) => myVar = str )}
    
    </div>
  }*/
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



