package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

class MyMod {
  //lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  //def howdy = "#time *" #> date.map(_.toString)
  def wow  = "#name *" #> "Nathaniel Allen Saul"
  
  //Once you launch SBT, type the container:start cOnce you launch SBT, type the container:start 
  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}
















