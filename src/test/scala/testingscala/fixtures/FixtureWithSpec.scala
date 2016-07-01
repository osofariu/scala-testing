package testingscala.fixtures

import org.scalatest._

class FixtureWithSpec extends FunSpec {

  override def withFixture(test: NoArgTest) = {

    val outcome = super.withFixture(test)
    outcome match {
      case result: Failed => {
        alert("Alert Action on: Failed")
      }
      case result: Canceled => {
        info("Info Action on: Canceled") // doesn't show up
      }
      case Succeeded => {
        note("Note on: Succeeded")
      }
      case Pending => {
        markup("Document on Pending")
      }
    }
    outcome
  }

  /*
    protected def info: Informer
    Returns an Informer that during test execution will forward strings passed to its apply method to the current reporter.
    If invoked in a constructor, it will register the passed string for forwarding later during test execution.
   */

  describe("withFixture, I can perform clean-up after each test, taking into account result of the test") {

    it("reports success when the test succeeds") {
      assert(1 === 1)
    }

    it("adds additional info when test fails") {
      fail("fail this test")
    }

    it("reports cancelled when the test was cancelled") {
      cancel("cancel this test")
    }

    it("pends this test") {
      pending
    }
  }
}