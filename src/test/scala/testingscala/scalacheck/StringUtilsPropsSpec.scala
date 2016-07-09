package testingscala.scalacheck

import org.scalacheck.Gen.{Choose, alphaChar, alphaStr, listOf, negNum, nonEmptyListOf, numChar, posNum}
import org.scalacheck.{Arbitrary, Gen, Prop, Properties}
import org.scalacheck.Prop._
import testingscala.scalacheck.StringUtils._

object StringUtilsPropsSpec extends Properties("StringUtils") {

  property("truncate") =
    Prop.forAll(alphaStr, posNum[Int]) {
      (s: String, n: Int) =>
        val t = truncate(s, n)
        val take = s.take(n)
        (s.length <= n && t == s) ||
          (s.length > n && (t == s.take(n)))
    }


  property("sternTruncate") =
    Prop.forAll(alphaStr, Arbitrary.arbInt.arbitrary) {
      (s: String, n: Int) =>
        if (n < 0) throws(classOf[IllegalArgumentException]) {
          sternTruncate(s, n)
        }
        else {
          val t = sternTruncate(s, n)
          val take = s.take(n)
          (s.length <= n && t == s) ||
            (s.length > n && (t == s.take(n)))
        }
    }

  val nonEmptyStrGen = nonEmptyListOf(alphaChar).map(_.mkString).suchThat(_.forall(_.isLetter))
  val nonEmptyListOfStrGen = nonEmptyListOf(nonEmptyStrGen)

  property("tokenize") =
    Prop.forAll(nonEmptyListOfStrGen, numChar) {
      (ts, d) => {
        val str = ts.mkString(d.toString)
        tokenize(str, d) ?= ts
      }
    }

  property("contains") = Prop.forAll {
    (s1: String, s2: String, s3: String) =>
      contains(s1 + s2 + s3, s2)
  }
}
