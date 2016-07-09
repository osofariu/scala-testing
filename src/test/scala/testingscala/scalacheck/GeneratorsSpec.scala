package testingscala.scalacheck

import org.scalacheck.{Prop, Properties}
import org.scalacheck.Prop.BooleanOperators

object GeneratorsSpec extends Properties("Generator Examples") {

  import org.scalacheck.Gen.choose

  val myGen = for {
    n <- choose(1, 50)
    m <- choose(n, 2 * n)
  } yield (n, m)


  property("verify myGen produced expected values, and label each condition") =
    Prop.forAll(myGen) { o =>
      val n = o._1
      val m = o._2

            (n >= 1) :| "n>=1" &&
           (n <= 50) :| "n <=50" &&
            (m >= n) :| "m >=n" &&
        (m <= 2 * n) :| "m <= 2+n"
    }
}
