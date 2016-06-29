package testingscala.asserts

import org.scalatest.exceptions.TestCanceledException
import testingscala.BaseSpec

class AssertsTest extends BaseSpec {

  describe("assert gives better information on errors") {
    val left = 1
    val right = 2

    it("tells you exactly where the error was") {
      assert(left == right)
      /*
        1 did not equal 2
        ScalaTestFailureLocation: testingscala.asserts.AssertsTest$$anonfun$1$$anonfun$apply$mcV$sp$1 at (AssertsTest.scala:12)
        org.scalatest.exceptions.TestFailedException: 1 did not equal 2
	      at org.scalatest.Assertions$class.newAssertionFailedException(Assertions.scala:528)
	      ....
       */
    }

    it("Scala's built-in assert is somewhat less useful") {
      scala.Predef.assert(left == right)
      /*
        assertion failed
        java.lang.AssertionError: assertion failed
	      at scala.Predef$.assert(Predef.scala:156)
	      at testingscala.asserts.AssertsTest$$anonfun$1$$anonfun$apply$mcV$sp$2.apply$mcV$sp(AssertsTest.scala:23)
	      ....
     */
    }

    it("also provides assertResult as another style for asserting expected values") {
      assertResult(2) {
        right - left
      }
      /*
        Expected 2, but got 1
        ScalaTestFailureLocation: testingscala.asserts.AssertsTest$$anonfun$1$$anonfun$apply$mcV$sp$3 at (AssertsTest.scala:34)
        org.scalatest.exceptions.TestFailedException: Expected 2, but got 1
	      at org.scalatest.Assertions$class.newAssertionFailedException(Assertions.scala:528)
       */
    }

    it("forcing failures, to make sure the code does not get to ") {
      val s = "hi"
      try {
        s.charAt(-1)
        fail()
      }
      catch {
        case _: IndexOutOfBoundsException => {} // expected to get here
      }
    }

    it("has assumption which throws TestCanceledException when assumption is not met") {
      assume(1 == 1)
      assume(1 != Some(1)) // this passes!  Will use superSafe later to catch issues like this
      try {
        assume(1 == 2) // this is the same as cancel()
      }
      catch {
        case e: TestCanceledException => {
          assert(e.message == None)
        }

        /*
        TestCanceledException is nice because it makes explicit the fact that there's a problem with
        running the test. The test didn't fail, because it couldn't even be run properly.
       */
      }
    }

    it("some additional support for checking assumptions") {
      try {
        cancel("problem here")
      }
      catch {
        case e: TestCanceledException => {
          assert(e.message.get == "problem here")
        }
      }
    }
  }
}
