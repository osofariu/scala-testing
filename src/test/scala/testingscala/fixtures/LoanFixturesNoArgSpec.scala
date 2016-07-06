package testingscala.fixtures

import java.io._
import java.util.UUID.randomUUID

import testingscala.BaseSpec
import testingscala.fixtures.DbServer._

class LoanFixturesNoArgSpec extends BaseSpec {

  def withDatabase(testCode: StringDatabase => Any) {
    val dbName = randomUUID.toString                                 // use random name to prevent test collision
    val database = createDb(dbName)
    try {
      database.insert("table1", "ScalaTest is ")                    // setup initial data
      testCode(database)                                            // "loan" the fixture to the test
    }
    finally removeDb(dbName)                                        // clean up the fixture
  }

  def withFile(testCode: (File, FileWriter) => Any) {
    val file = File.createTempFile("hello", "world") // create the fixture
    val writer = new FileWriter(file)
    try {
      writer.write("ScalaTest is ")
      testCode(file, writer)
    }
    finally writer.close()
  }

  describe("using fixtures I give my test access to setup it needs") {

    it("passing a string to the file Writer I can confirm file length has increased") {
      withFile { (file, writer) =>
        writer.write("productive!")
        writer.flush()
        assert(file.length === 24)
      }
    }

    it("inserting a string in a table, I can find it in that table")(withDatabase { db =>
      db.insert("table1", "readable!")
      assert(db.select("table1") === "ScalaTest is readable!")
    })


    it("I can use both fixtures at the same time!") {
      withDatabase { db =>
        withFile { (file, writer) =>
          db.insert("table1", "clear!")
          writer.write("concise!")
          writer.flush()
          assert(db.select("table1") === "ScalaTest is clear!")
          assert(file.length === 21)
        }
      }
    }
  }
}
