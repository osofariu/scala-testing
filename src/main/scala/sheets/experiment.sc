
object Example {
  private[Example] class ValidatedName(val s: String) {
     if (s.length>0 && s(0).isUpper) s
     else
       throw new IllegalArgumentException(s+" is not a name!")
  }

  class Person(var firstName: ValidatedName, var lastName: String) { }
  implicit def string2valid(s: String) : ValidatedName = new ValidatedName(s)
  implicit def valid2string(v: ValidatedName) : String = v.s
}

new Example.Person("Joe","Schmoe")
//new Example.Person("ee","cummings")

import scala.collection.immutable
var m = immutable.Map(1 -> "one", 2 -> "two")
m(1)
m += (3 -> "three")
m.values.mkString(",")



var m2 = immutable.HashMap(10 -> "ten")
m2 += (11 -> "eleven")

import scala.collection.mutable

val m2mod = mutable.HashMap(100 -> "hundred")
m2mod += (200 -> "two hundred")

def gcd(a: Int, b: Int): Int = {
  printf("a: %5d; b: %5d\n", a, b);
  if (b == 0) a else gcd(b, a % b)
}

gcd(100, 12)

val `the big wolf` = 12
`the big wolf` + 1
