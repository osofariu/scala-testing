package testingscala.scalacheck

import org.scalacheck.{Prop, Properties}

object BasicScalacheckProperties extends Properties("Simple Math"){
  property("Sums are associative") = Prop.forAll {(x:Int, y:Int) => x+y == y+x}
}


