package testingscala.fixtures

import java.util.UUID._
import testingscala.fixtures.DbServer._
import org.scalatest.fixture

class WithFixtureOneArgSpec extends fixture.FunSpec {

  type FixtureParam = StringDatabase

  override def withFixture(test: OneArgTest) = {
    val dbName = randomUUID.toString
    val database = createDb(dbName)
    try {
      database.insert("table1", "ScalaTest is ")   // simulate initial setup
      withFixture(test.toNoArgTest(database))
    }
    finally removeDb(dbName)
  }

  describe("using fixtures I give my test access to setup it needs") {
    it("inserting a string in a table, I can find it in that table") { database =>
      database.insert("table1", "readable!")
      assert(database.select("table1") === "ScalaTest is readable!")
    }
  }
}

