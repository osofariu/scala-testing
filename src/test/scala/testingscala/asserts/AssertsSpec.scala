package testingscala.asserts

import org.scalatest.exceptions.{TestCanceledException, TestFailedException}
import testingscala.BaseSpec

class AssertsSpec extends BaseSpec {

  describe("scalatest assert gives better information on errors than scala's assert") {
    val left = 1
    val right = 2

    it("tells you exactly where the error was") {
      val result = intercept[TestFailedException] {
        assert(left == right)
      }
      assert(result.getMessage() == "1 did not equal 2")
    }


    it("Scala's built-in assert is somewhat less useful") {
      val result = intercept[AssertionError] {
        scala.Predef.assert(left == right)
      }
      assert(result.getMessage == "assertion failed")
    }


    it("also provides assertResult as another style for asserting expected values") {
      val result = intercept[TestFailedException] {
        assertResult(2) {
          right - left
        }
      }
      assert(result.getMessage() == "Expected 2, but got 1")
    }

    it("you can force failures, to make sure the code does not get to that point") {
      val s = "hi"
      try {
        s.charAt(-1)
        fail()
      }
      catch {
        case _: IndexOutOfBoundsException => {} // expected to get here
      }
    }


    it("you can set up assumptions to make sure your tests have what they need") {
      assume(1 == 1)
      val result = intercept[TestCanceledException] {
        assume(1 == 2) // this is the same as cancel()
      }

      assert(result.message.isEmpty)
    }

    it("you can assertively cancel a test if assumptions are not met") {
      val result = intercept[TestCanceledException] {
        cancel("we have a problem here")
      }

      assert(result.getMessage() == "we have a problem here")
    }
  }
}
