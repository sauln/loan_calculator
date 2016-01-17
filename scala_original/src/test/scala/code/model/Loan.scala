package code
package model

import net.liftweb._
import http._
import net.liftweb.util._
import net.liftweb.common._
import Helpers._
import lib._

//import code.model.Loan
//import org.scalatest.FlatSpec
//import org.scalatest.FeatureSpec

import org.specs2.mutable.Specification
import org.specs2.specification.AroundExample
import org.specs2.execute.Result




object LoanSpec extends Specification{

	"this test" should {
		"always pass" in {
			"dog" must startWith("d")
		}
	}

	"an empty loan" should {
		"acrue no interest" in {
			//how do I get Loan in?
			val o = Loan(0,0,0)

		}
	}


}


//import code.model._



/**
 *  the test should define how the loan behaves
 *  a loan should:
 *  	calculate the interest in a month
 *		make a payment to the loan
 *			
 */

/*
class LoanSpec extends FlatSpec {
	"An empty loan" should "acrue no interest" in {
		val o = Loan(0,10,10);
		val interest = o.interest()
		assert(interest === 0.0)


	}
}



class SetSpec extends FlatSpec {

	"An empty Set" should "have size 0" in {
		assert(Set.empty.size == 0)
	}

	it should "produce NoSuchElementException when head is invoked" in {
		intercept[NoSuchElementException] {
			Set.empty.head
		}
	}
}
*/



















