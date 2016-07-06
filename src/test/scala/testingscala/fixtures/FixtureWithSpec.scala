package testingscala.fixtures

import java.io.{File, FileReader, FileWriter}

import org.scalatest._

class FixtureWithSpec extends FunSpec {

  //The withFixture(NoArgTest) method exists so that you can override it
  // and set a fixture up before, and clean it up after, each test.

  private var reader: FileReader = _
  val textInFile = "Hello, test!"

  override def withFixture(test: NoArgTest) = {
    val fileName = "TempFile.txt"
    val writer = new FileWriter(fileName)

    try {
      writer.write(textInFile)
    }
    finally {
      writer.close()
    }

    reader = new FileReader(fileName)
    try {
      val outcome = super.withFixture(test) // execute the test

      outcome match {
        case result: Failed => {
          alert("Test Failed")
        }
        case result: Canceled => {
          note("Test was canceled")
        }
        case Succeeded => {
          note("Test Succeeded")
        }
        case Pending => {
          markup("Test marked as Pending")
        }
      }

      outcome
    }
    finally {
      reader.close()
      val file = new File(fileName)
      file.delete()   // gets deleted after the test is run
    }

  }


  describe("withFixture, after each test I can alert about result of the test or can perform clean-up") {

    it("Check that the right stuff was written to the file") {
      val builder = new StringBuilder
      var c = reader.read()
      while (c != -1) {
        builder.append(c.toChar)
        c = reader.read()
      }
      assert(builder.toString === textInFile)
    }
  }
}
