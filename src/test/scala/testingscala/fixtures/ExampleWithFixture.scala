package testingscala.fixtures

import org.scalatest.FlatSpec
import org.scalatest.exceptions.TestFailedException

class ExampleWithFixture extends FlatSpec {

  import java.io.File
  import org.scalatest._

  override def withFixture(test: NoArgTest) = {

    super.withFixture(test) match {
      case failed: Failed =>
        val currDir = new File(".")
        val fileNames = currDir.list()
        info("Dir snapshot: " + fileNames.mkString(", "))
        failed
      case other => other
    }
  }

  "This test" should "succeed" in {
    assert(1 + 1 === 2)
  }

  it should "fail" in {
    val thrown = intercept[TestFailedException] {
      assert(1 + 1 === 3)
    }
    assert (thrown.message contains "2 did not equal 3")
  }

/*
additional OUTPUT:

2 did not equal 3
ScalaTestFailureLocation: testingscala.fixtures.ExampleWithFixture$$anonfun$2 at (ExampleWithFixture.scala:27)
org.scalatest.exceptions.TestFailedException: 2 did not equal 3
	at org.scalatest.Assertions$class.newAssertionFailedException(Assertions.scala:528)
	at org.scalatest.FlatSpec.newAssertionFailedException(FlatSpec.scala:1685)
	at org.scalatest.Assertions$AssertionsHelper.macroAssert(Assertions.scala:501)
	at testingscala.fixtures.ExampleWithFixture$$anonfun$2.apply(ExampleWithFixture.scala:27)
	at testingscala.fixtures.ExampleWithFixture$$anonfun$2.apply(ExampleWithFixture.scala:27)
	at org.scalatest.OutcomeOf$class.outcomeOf(OutcomeOf.scala:85)
	at org.scalatest.OutcomeOf$.outcomeOf(OutcomeOf.scala:104)
*/

}
