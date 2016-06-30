import scala.collection.mutable.ListBuffer

def fixture =
  new {
    val builder = new StringBuilder("ScalaTest is")
    val buffer = new ListBuffer[String]
  }


val v1 = fixture
val v2 = fixture
