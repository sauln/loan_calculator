package com.pocketchangeapp.snippet



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


/*
class HomePage {
  def summary (xhtml : NodeSeq) : NodeSeq =  {
 
      val entries : NodeSeq =  {
        
        accounts.flatMap({account =>
          bind("acct", chooseTemplate("account", "entry", xhtml),
               "name" -> <a href={"/account/" + account.name.is}>
                            {account.name.is}</a>,
               "balance" -> Text(account.balance.toString))
        })
      }
      bind("account", xhtml, "entry" -> entries)
  }
}


*/










