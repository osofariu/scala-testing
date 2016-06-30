package testingscala.fixtures

import testingscala.BaseSpec

import scala.collection.mutable.ListBuffer

class FixtureSpec extends BaseSpec {

  describe("shared fixtures for all tests, without clean-up") {
    def fixture =
      new {
        val builder = new StringBuilder("ScalaTest")
        val buffer = new ListBuffer[String]
      }

    it("uses fixture object") {
      val f = fixture
      f.builder.append(" is useful")
      f.builder.toString should equal("ScalaTest is useful")
      f.buffer shouldBe empty
    }

    it("you can change the object created by the fixture without affecting other tests") {
      val f = fixture
      import f._

      buffer += "something"
      buffer.size shouldBe 1
      buffer.head shouldBe "something"
      builder.toString shouldBe "ScalaTest"
    }
  }

  describe("tests choose fixtures to use, without clean-up") {

    trait Builder {
      val builder = new StringBuilder("ScalaTest")
    }

    trait Buffer {
      val buffer = ListBuffer("ScalaTest", "is", "cool")
    }

    it("uses builder alone as a fixure") {
      new Builder {
        assert(builder.toString === "ScalaTest")
      }
    }

    it("uses both builer and buffer") {
      new Builder with Buffer {
        assert(builder.toString === "ScalaTest")
        assert(buffer.size === 3)
      }
    }
  }
}
