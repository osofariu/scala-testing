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


    // Set up the temp file needed by the test
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
