package testingscala.fixtures

import org.scalatest._

class FixtureWithSpec extends FunSpec {

  override def withFixture(test: NoArgTest) = {

    val outcome = super.withFixture(test)
    outcome match {
      case result: Failed => {
        alert("*** ALERT on: Failed")
      }
      case result: Canceled => {
        info("*** INFO on: Canceled") // doesn't show up
      }
      case Succeeded => {
        note("*** NOTE on: Succeeded")
      }
      case Pending => {
        markup("*** MARKUP on Pending")
      }
    }
    outcome
  }


  describe("withFixture, after each test I can alert about result of the test or can perform clean-up") {

    it("Notes success when the test succeeds") {
      assert(1 === 1)
    }

    it("Alerts of errors when test fails") {
      fail("fail this test")
    }

    it("info of cancelled when the test was cancelled") {
      cancel("cancel this test")
    }

    it("documents pending the test") (pending)


   ignore ("will not get run.. like pending, but different!") {
      fail("this couldn't possibly work")
    }
  }
}