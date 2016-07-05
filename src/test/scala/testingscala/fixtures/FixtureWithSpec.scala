package testingscala.fixtures

import java.io.{File, FileReader, FileWriter}

import org.scalatest._

class FixtureWithSpec extends FunSpec {

  //The withFixture(NoArgTest) method exists so that you can override it
  // and set a fixture up before, and clean it up after, each test.

  private var reader: FileReader = _
  val textInFile = "Hello, test!"

  override def withFixture(test: NoArgTest) = {
    val FileName = "TempFile.txt"
    val writer = new FileWriter(FileName)

    try {
      writer.write(textInFile)
    }
    finally {
      writer.close()
    }

    reader = new FileReader(FileName)
    try {
      val outcome = super.withFixture(test) // execute the test

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
    finally {
      // Close and delete the temp file
      reader.close()
      val file = new File(FileName)
      file.delete()
    }

  }


  describe("withFixture, after each test I can alert about result of the test or can perform clean-up") {

    it("Notes success when the test succeeds") {
      val builder = new StringBuilder
      var c = reader.read()
      while (c != -1) {
        builder.append(c.toChar)
        c = reader.read()
      }
      assert(builder.toString === textInFile)
    }

    it("Alerts of errors when test fails") {
    //  fail("fail this test")
    }

    it("info of cancelled when the test was cancelled") {
    //  cancel("cancel this test")
    }

    it("documents pending the test") (pending)  // not written yet


   ignore ("will not get run.. temporarily skipping this test!") {
      fail("this couldn't possibly work")
    }
  }
}

/*
> testOnly *FixtureWithSpec
[info] FixtureWithSpec:

// SUCCESS
[info] withFixture, after each test I can alert about result of the test or can perform clean-up
[info]   + *** NOTE on: Succeeded
[info] - Notes success when the test succeeds

// FAILED
[info]   + *** ALERT on: Failed
[info] - Alerts of errors when test fails *** FAILED ***
[info]   fail this test (FixtureWithSpec.scala:74)

// CANCELED
[info] - info of cancelled when the test was cancelled !!! CANCELED !!!
[info]   cancel this test (FixtureWithSpec.scala:78)
[info]   + *** INFO on: Canceled

//PENDING
[info] - documents pending the test (pending)
[info]   + *** MARKUP on Pending
[info] - will not get run.. like pending, but different! !!! IGNORED !!!

[info] ScalaTest
[info] Run completed in 205 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 1, canceled 1, ignored 1, pending 1
[info] *** 1 TEST FAILED ***

[error] Failed: Total 2, Failed 1, Errors 0, Passed 1, Ignored 1, Canceled 1, Pending 1
[error] Failed tests:
[error]         testingscala.fixtures.FixtureWithSpec
[error] (test:testOnly) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 0 s, completed Jul 4, 2016 11:00:19 PM

 */
