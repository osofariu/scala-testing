package testingscala.fixtures

import org.scalatest.{FlatSpec, _}

class FixtureWithSpec extends FlatSpec {

  override def withFixture(test: NoArgTest) = {

    super.withFixture(test) match {
      case result: Failed => {
        info("************** FAILED ****************")
        result
      }
      case result: Canceled => {
        info("************** CANCELED ****************")
        result
      }
      case other => {
        if (other.isSucceeded) {
          info("************** SUCCEEDED ****************")
        }
        if (other.isPending) {
          info("************** PENDING ****************")
        }
        other
      }
    }
  }

  "withFixture"  should "add additional info when test fails" in {
    assert(false)
  }

  it should "be fine when the test succeeds" in {
    assert(true)
  }
}
