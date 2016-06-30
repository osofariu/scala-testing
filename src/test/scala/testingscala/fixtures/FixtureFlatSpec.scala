package testingscala.fixtures

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable.ListBuffer


class FixtureFlatSpec extends FlatSpec with Matchers {

  trait Builder {
    val builder = new StringBuilder("ScalaTest")
  }

  trait Buffer {
    val buffer = ListBuffer("ScalaTest", "is", "cool")
  }

  it should "be clear what fixture the test is using " in new Builder with Buffer {
    assert(builder.toString === "ScalaTest")
    assert(buffer.size === 3)
  }
}