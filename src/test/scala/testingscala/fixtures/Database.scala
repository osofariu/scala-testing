package testingscala.fixtures

import java.util.concurrent.ConcurrentHashMap

trait Database[V] {
  type DB = ConcurrentHashMap[String, V]
  def initDb(): DB
  def insert(k: String, v: V)
  def select(k: String): V
}

class StringDatabase extends Database[String] {
  var db = initDb()
  override def initDb(): DB = {
    db = new DB()
    db
  }
  override def insert(table: String, value: String): Unit = {
    if (db.get(table) != null) {
      db.put(table, db.get(table) + value)
    } else {
      db.put(table, value)
    }
  }
  override def select(k: String): String = db.get(k)
}

object DbServer {
  private val databases = new ConcurrentHashMap[String, StringDatabase]
  def createDb(name: String): StringDatabase = {
    val db = new StringDatabase()
    databases.put(name, db)
    db
  }
  def removeDb(name: String) {
    databases.remove(name)
  }
}
