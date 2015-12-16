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


import com.pocketchangeapp.model._

import java.util.Date

class AddEntry extends StatefulSnippet {

  def dispatch = {
    case "addentry" => add _
  }

  var account : Long = _
  var date = ""
  var desc = ""
  var value = ""

  var tags = S.param("tag") openOr ""

  def add(in: NodeSeq): NodeSeq =  {
    def doTagsAndSubmit(t: String) {
      tags = t
      if (tags.trim.length == 0)
        S.error("We're going to need at least one tag.")
      else {
        val entryDate = date
        val amount = BigDecimal(value)
        val currentAccount = Account.find(account).open_!

          //val (entrySerial, entryBalance) =
          //  Expense.getLastExpenseData(currentAccount, entryDate)

          /*val e = Expense.create.account(account)
                    .dateOf(entryDate)
                    .serialNumber(entrySerial + 1)
                    .description(desc)
                    .amount(BigDecimal(value)).tags(tags)
                    .currentBalance(entryBalance + amount)
          e.validate match {
            case Nil => {
              //Expense.updateEntries(entrySerial + 1, amount)
              e.save
              val acct = Account.find(account).open_!
              val newBalance = acct.balance.is + e.amount.is
              acct.balance(newBalance).save
              S.notice("Entry added!")
              unregisterThisSnippet()
            }
            case x => error(x)
          }
          */
      }
   }
   
   bind("e", in,
        //"account" -> select(allAccounts, Empty,
        //                    id => account = id.toLong),
        "dateOf" -> text("11/11/2015",
                         date = _,
                         "id" -> "entrydate"),
        "desc" -> text("Item Description", desc = _),
        "value" -> text("Value", value = _),
        "tags" -> text(tags, doTagsAndSubmit))
    }
  
}






