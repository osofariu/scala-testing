package testingscala.report

import org.scalatest.GivenWhenThen
import org.scalatest.exceptions.TestFailedException
import org.scalatest.FunSpec
class ReportOnSpec extends FunSpec with GivenWhenThen {

  describe("When I run a test I can report useful information about it") {

    it("I will see notifications and alerts immediately") {
      note("Test1/note: empty set must be empty. Yay.")
      alert("Test1/alert: this may be seen before the output for the test itself")
      assert(Set().empty.isEmpty)
    }
  }

  describe("I will see info and markup after the results of the test are known") {

    it("info and markup are shown in green if the test was successful") {
      markup("this test intercepts an expected exception")
      intercept[TestFailedException] {
        assert(Set().empty.nonEmpty)
      }
      info("the exception was of type: TestFailedException")
    }

    it("info and markup are shown in red if the test was unsuccessful") {

      Given("I have a set")
      var s = scala.collection.mutable.Set[Int]().empty
      info("info: I have an empty set")

      When("I add an element to the set")
      s += 12
      markup("markup: s has element 12 in it")

      Then("the set contains that element")
      markup {
        """
          | I'm supposed to assert that the set contains 13.. yikes!
          | which makes me feel uncomfortable, because it's not true!
        """.stripMargin
      }
      assert(s contains 13)
    }
  }
}
