package testingscala.fixtures

import java.util.concurrent.ConcurrentHashMap

import testingscala.BaseSpec

object DbServer {
  // Simulating a database server
  type Db = StringBuffer
  private val databases = new ConcurrentHashMap[String, Db]
  def createDb(name: String): Db = {
    val db = new StringBuffer
    databases.put(name, db)
    db
  }
  def removeDb(name: String) {
    databases.remove(name)
  }
}

import java.io._
import java.util.UUID.randomUUID

import testingscala.fixtures.DbServer._

class LoanFixturesSpec extends BaseSpec {

  def withDatabase(testCode: Db => Any) {
    val dbName = randomUUID.toString
    val db = createDb(dbName) // create the fixture
    try {
      db.append("ScalaTest is ") // perform setup
      testCode(db) // "loan" the fixture to the test
    }
    finally removeDb(dbName) // clean up the fixture
  }

  def withFile(testCode: (File, FileWriter) => Any) {
    val file = File.createTempFile("hello", "world") // create the fixture
    val writer = new FileWriter(file)
    try {
      writer.write("ScalaTest is ") // set up the fixture
      testCode(file, writer) // "loan" the fixture to the test
    }
    finally writer.close() // clean up the fixture
  }

  describe("using fixtures.. TBD") {

    // This test needs the file fixture
    it("Testing should be productive") {
      withFile { (file, writer) =>
        writer.write("productive!")
        writer.flush()
        assert(file.length === 24)
      }
    }

    //  FlatSpec looks a little nicer
    //    "Test code" should "be readable" in withDatabase { db =>
    //        db.append("readable!")
    //      assert(db.toString === "ScalaTest is readable!")
    //    }

    it("Test code should be readable")(withDatabase { db =>
      db.append("readable!")
      assert(db.toString === "ScalaTest is readable!")
    })


    it("should be clear and concise") {
      withDatabase { db =>
        withFile { (file, writer) =>
          db.append("clear!")
          writer.write("concise!")
          writer.flush()
          assert(db.toString === "ScalaTest is clear!")
          assert(file.length === 21)
        }
      }
    }
  }
}
