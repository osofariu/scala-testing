package testingscala.fixtures

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class ExampleWithFixture extends FlatSpec with Matchers with BeforeAndAfter {

  trait Fixture {
    val intValue = 20
    val strValue = "twenty"
  }

  trait TheOtherFixture {
    val theOtherOne = 99
  }

  def objectFixture = new {
    val intValue = 30
    val strValue = "boo"
  }

  before {
    info("before test")
  }

  "Using trait-based fixture" should "give us a way to re-use fixture code" in {
    new Fixture with TheOtherFixture {
      intValue shouldEqual 20
      strValue shouldBe "twenty"
      theOtherOne shouldBe 99
    }
  }

  "Using anonymous object fixtures" should "give us another" in {
    objectFixture.intValue shouldEqual 30
    assert(objectFixture.strValue === "boo")
  }

  after {
    info("after test")
  }
}



// talk a little about Scalactic if I haven't already.
