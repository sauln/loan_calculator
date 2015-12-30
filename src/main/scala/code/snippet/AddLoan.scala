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

import net.liftweb.common.Full
import net.liftweb.http.S
import net.liftweb.util.PassThru
import scala.xml.{NodeSeq, Text, XML}

import code.model._



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
 * WANT:  when button is pushed, pull data into an object that is maintained per user
 *        also generate new line of input data and turn input fields where loan was input 
 *        into display values.
 */




object LoanInput {
  private object whence extends RequestVar(S.referer openOr "/")
  
  def render = {
    val w = whence.is
    
    for {
      b <- S.param("balance")
      i <- S.param("interest")
      m <- S.param("minimum")
    } {
      Loans.add(b, i, m)
      
      val xml: NodeSeq = Loans.show
      SetHtml("results", xml )
      
      S.notice(Loans.show)
      S.redirectTo(w)
    }
  
  
  PassThru
  }


  def add_loans = 
   "* [onClick]" #> ajaxInvoke(() => {
      val xml: NodeSeq = Loans.show
      SetHtml("results", xml )
      //S.redirectTo(w)
    })

  def calculate_values = 
    "* [onClick]" #> ajaxInvoke(() => {
      val xml: NodeSeq = Loans.show_sum
      SetHtml("results", xml )
    })
}


