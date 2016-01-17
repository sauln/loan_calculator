package code
package snippet

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




object snipSpecs2Spec extends Specification with AroundExample {
	val session = new LiftSession("", randomString(20), Empty)
	val stableTime = now

	def around[T <% Result](body: =>T) = {
		S.initIfUninitted(session) {
			DependencyFactory.time.doWith(stableTime) {
				body // execute t inside a http session
			}
		}
	}



	"this test" should {
		"always pass" in {
			"dog" must startWith("d")
		}
	}

	"an empty loan" should {
		"acrue no interest" in {
			val o = 5
			
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



















