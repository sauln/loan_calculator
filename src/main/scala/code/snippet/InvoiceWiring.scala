package code
package snippet

import net.liftweb._
import http._
import SHtml._
import util._
import Helpers._
import js._
import js.JsCmds._
import js.jquery._

import scala.xml.{NodeSeq, Text}


// got the original for this code at seventhings.liftweb.net/wiring
// will use this as a basis for the loan input system


case class Loan(guid: String, 
                name: String, 
                balance: Double, 
                interest_rate: Double, 
                minimum: Double)
//case class Line(guid: String, name: String, price: Double, taxable: Boolean)

class LoanWiring {




  /**
   * define the relationships amoung items
   * notice its singleton object - 
   */
  private object Info {

    val loans = ValueCell(List(newLoan))//declares a new line
    //val taxRate = ValueCell(0.05d)//sets default taxRate
    
    val subtotal = loans.lift(_.foldLeft(0d)(_ + _.balance))
    
    val interest = loans.lift(_.foldLeft(0d)
                    {case (a, b) => a + (b.balance * b.interest_rate)})
    //val interest = loans.map((a) => (a.balance *  (a.interest_rate/12)))
                    //    .lift(_.foldLeft(0d)(_ + _))

    val total   = subtotal.lift(interest) {_ * _}

    //not sure what lift does - maybe pulls the value out of the result of the fold?
    //val subtotal = invoices.lift(_.foldLeft(0d)(_ + _.price))
    
    //sums just taxable - also unsure of lift in this situation
    //val taxable = invoices.lift(_.filter(_.taxable).foldLeft(0D)(_+_.price))

    //val tax = taxRate.lift(taxable){_ * _}// all this does is multiply taxRate and taxable
                                          // taxRate must need lifting out of the ValueCell?
    //val total = subtotal.lift(tax) {_ + _}// is lift partially applying the function?
                                          // does lift temporarily lift the value out of
                                          // some wrapper, do the calculation, then put it
                                          // back in the wrapper?




  }


  // these are the calculated elements that are wired to input values
  def subtotal = WiringUI.toNode(Info.subtotal)(doubleDraw)
  def interest = WiringUI.toNode(Info.interest, JqWiringSupport.fade)(doubleDraw)
  def total = WiringUI.toNode(Info.total, JqWiringSupport.fade)(doubleDraw)


  //def taxRate = ajaxText(Info.taxRate.get.toString, 
   //                      doubleToJsCmd(Info.taxRate.set))


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

  /**
   * render a line of input fields
   */

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

    //{ajaxCheckbox(theLine.taxable,
    //            b => mutateLine(guid) {_.copy(taxable = b)})}
  private def newLoan = Loan(nextFuncName, "name", 0, 0,0)

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











































