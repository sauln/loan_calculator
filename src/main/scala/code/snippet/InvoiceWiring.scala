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



case class Line(guid: String, name: String, price: Double, taxable: Boolean)

class InvoiceWiring {




  /**
   * define the relationships amoung items
   * notice its singleton object - 
   */
  private object Info {

    val invoices = ValueCell(List(newLine))//declares a new line
    val taxRate = ValueCell(0.05d)//sets default taxRate
    
    //not sure what lift does - maybe pulls the value out of the result of the fold?
    val subtotal = invoices.lift(_.foldLeft(0d)(_ + _.price))
    
    //sums just taxable - also unsure of lift in this situation
    val taxable = invoices.lift(_.filter(_.taxable).foldLeft(0D)(_+_.price))

    val tax = taxRate.lift(taxable){_ * _}// all this does is multiply taxRate and taxable
                                          // taxRate must need lifting out of the ValueCell?
    val total = subtotal.lift(tax) {_ + _}// is lift partially applying the function?
                                          // does lift temporarily lift the value out of
                                          // some wrapper, do the calculation, then put it
                                          // back in the wrapper?




  }



  /**
   *  wire an element to subtotal
   */
  def subtotal = WiringUI.toNode(Info.subtotal)(doubleDraw)


  /**
   * wire elemnt to taxable
   */
  def taxable = WiringUI.toNode(Info.taxable, JqWiringSupport.fade)(doubleDraw)

  // etc
  def tax = WiringUI.toNode(Info.tax, JqWiringSupport.fade)(doubleDraw)

  def total = WiringUI.toNode(Info.total, JqWiringSupport.fade)(doubleDraw)


  /**
   * tax rate input
   */
  def taxRate = ajaxText(Info.taxRate.get.toString, 
                         doubleToJsCmd(Info.taxRate.set))


  /**
   * draw all the input lines
   * 
   */
  def showLines = "* *" #> (Info.invoices.get.flatMap(renderLine): NodeSeq)

  /**
   * add a line to the input
   */
  def addLine = 
    "* [onclick]" #> ajaxInvoke(() =>
      JqJsCmds.AppendHtml("invoice_lines", renderLine(appendLine)))

  /**
   * render a line of input fields
   */

  private def renderLine(theLine: Line): NodeSeq = {
    import theLine._

    <div id={guid}>
    {ajaxText(name, s => mutateLine(guid)(_.copy(name = s)))}

    {ajaxText(price.toString,
                (d: Double) => mutateLine(guid) {_.copy(price = d)})}

    {ajaxCheckbox(theLine.taxable,
                b => mutateLine(guid) {_.copy(taxable = b)})}
    </div>
  }

  private def newLine = Line(nextFuncName, "", 0, false)

  private def appendLine: Line = {
    val ret = newLine
    Info.invoices.set(ret :: Info.invoices.get)
    ret
  }

  /**
   * Mutate a line and update the info field
   */

  private def mutateLine(guid: String)(f: Line => Line) {
    val all = Info.invoices.get
    val head = all.filter(_.guid == guid).map(f)
    val rest = all.filter(_.guid != guid)
    Info.invoices.set(head ::: rest)
  }


  // convert a Double to a NodeSeq
  private def doubleDraw: (Double, NodeSeq) => NodeSeq =
    (d, ns) => Text(java.text.NumberFormat.getCurrencyInstance.format(d))


  // "helpful implicit conversions
  private implicit def unitToJsCmd(in: Unit): JsCmd = Noop
  private implicit def doubleToJsCmd(in: Double => Any): String => JsCmd =
    str => {asDouble(str).foreach(in)}
  }











































