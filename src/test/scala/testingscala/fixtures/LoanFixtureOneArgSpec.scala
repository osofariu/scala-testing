package testingscala.fixtures

import java.util.UUID._
import testingscala.fixtures.DbServer._

class LoanFixtureOneArgSpec extends org.scalatest.fixture.FunSpec {

  case class FixtureParam(database: StringDatabase)

  def withFixture(test: OneArgTest) = {
    val dbName = randomUUID.toString
    val database = createDb(dbName)
    val dbFixture = FixtureParam(database)
    try {
      database.insert("table1", "ScalaTest is ")
      withFixture(test.toNoArgTest(dbFixture))
    }
    finally removeDb(dbName)
  }

  describe("using fixtures I give my test access to setup it needs") {

    it("inserting a string in a table, I can find it in that table") { f =>
      f.database.insert("table1", "readable!")
      assert(f.database.select("table1") === "ScalaTest is readable!")
    }
  }
}

