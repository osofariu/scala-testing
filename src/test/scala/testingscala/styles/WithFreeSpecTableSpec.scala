package testingscala.styles

import org.scalatest.{FunSpec, Matchers}
import org.scalatest.prop.TableDrivenPropertyChecks

import scala.collection.BitSet
import scala.collection.immutable.{HashSet, TreeSet}

class WithFreeSpecTableSpec extends org.scalatest.FreeSpec with TableDrivenPropertyChecks with Matchers {

  val examples =
    Table(
      "set",
      BitSet.empty,
      HashSet.empty[Int],
      TreeSet.empty[Int]
    )

  "an empty Set should have size 0" in {
    forAll(examples) { set =>
      set.size should be(0)
    }
  }

  "invoking head on an empty set should produce NoSuchElementException" in {
    forAll(examples) { set =>
      a[NoSuchElementException] should be thrownBy {
        set.head
      }
    }
  }
}
