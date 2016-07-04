package testingscala.tags

import org.scalatest.Tag
import testingscala.BaseSpec

class TagTest extends BaseSpec {

  describe("built-in tags") {
    ignore("will ignore this because I replaced 'it' with 'ignore'") {
      fail("should not actually fail")
    }
 }

  describe("custom tags allow me to select which tests to run") {

    // you will likely want these in BaseSpec to re-use for all tests
    object SlowTest extends Tag("SlowTest")
    object FastTest extends Tag("FastTest")

    it("will get run when choosing to run slow tests", SlowTest) {
      Thread.sleep(4000)
    }

    it("will run pretty fast", FastTest) {
      Thread.sleep(50)
    }


    it("will run much faster", FastTest) {
      2 should equal(2)
    }
  }
}
