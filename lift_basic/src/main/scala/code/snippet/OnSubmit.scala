package code
package snippet

import net.liftweb._
import http._
import util.Helpers._
import scala.xml.NodeSeq


/**
 * A snippet that binds behavior, functions,
 * to HTML elements
 */

object OnSubmit {
  def render = {
    // define some variables
    var name = ""
    var age = 0

    def process() {
      if (age < 13) S.error("Too young!")
      else {
        // otherwise give the user feedback and 
        // redirect to the home page
        S.notice("Other:" +other)
        S.notice("Name: "+name)
        S.notice("Age: "+age)
        S.redirectTo("/")
      }
    }

    // associate each of the form elements
    // with a function.  Behavio to perform 
    // when the form element is submitted
    "name=other" #> SHtml.onSubmit(other = _) &
    "name=name" #> SHtml.onSubmit(name = _) & //set the name
    // set the age variable if we can convert to an Int
    "name=age"  #> SHtml.onSubmit(s => asInt(s).foreach(age = _)) &
    // when the form is submitted, process the variable
    "type=submit" #> SHtml.onSubmitUnit(process)
  }
}







