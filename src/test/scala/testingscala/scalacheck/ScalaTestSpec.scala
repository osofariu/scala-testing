package testingscala.scalacheck

import org.scalacheck.{Prop, Properties}
import org.scalatest.{Matchers, Spec}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import testingscala.BaseSpec

class ScalaTestSpec extends  BaseSpec with GeneratorDrivenPropertyChecks {
  describe("We can use test data from Scala check") {
    it("runs the same but with different constructs") {
      forAll {
        (a: Int, b: Int) =>
          (a + b) should be(b + a)
      }
    }
  }
}

