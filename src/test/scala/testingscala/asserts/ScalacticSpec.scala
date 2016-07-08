package testingscala.asserts

import org.scalactic.{Equality, Uniformity}
import testingscala.BaseSpec

class ScalacticSpec extends BaseSpec {


  describe("Normalize number values to make them comparable") {

    val truncated =
      new Uniformity[Double] {
        def normalized(d: Double) = d.floor
        def normalizedCanHandle(o: Any) = o.isInstanceOf[Double]
        def normalizedOrSame(o: Any): Any =
          o match {
            case d: Double => normalized(d)
            case _ => o
          }
      }

    implicit val doublesMostlyEqual =
      new Equality[Double] {
        override def areEqual(a: Double, b: Any): Boolean = b match {
          case i: Int => areEqual(a, i.toDouble)
          case d: Double => a.floor == d.floor
          case _ => false
        }
      }

    it("when comparing two numbers we can elegantly state we can use the significant portion") {
      123.0 should equal(123.9)(after being truncated)
    }

    it("you can customize equality to suit your needs") {

      assert(120.0 === 120.4)
      assert(120.9 === 120.0)
      assert(120.4 === 120)
    }
  }

  describe("") {
    val lowercased =
      new Uniformity[String] {
        override def normalizedOrSame(b: Any): Any = b match {
          case b: String => normalized(b)
          case _ => b
        }
        override def normalizedCanHandle(b: Any): Boolean = b.isInstanceOf[String]
        override def normalized(a: String): String = a.toLowerCase
      }

    val trimmed =
      new Uniformity[String] {
        override def normalizedOrSame(b: Any): Any = b match {
          case s: String => normalized(s)
          case _ => b
        }
        override def normalizedCanHandle(b: Any): Boolean = b.isInstanceOf[String]
        override def normalized(a: String): String = a.trim
      }

    it("strings can be compared more elegantly with custom equality") {

      "this string" should equal("This string")(after being lowercased)
      "this string" should equal("THIS STRING  ")(after being lowercased and trimmed)
    }
  }
}
