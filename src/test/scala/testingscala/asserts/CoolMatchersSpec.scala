package testingscala.asserts

import testingscala.BaseSpec
import org.scalactic.Equality

class CoolMatchersSpec extends BaseSpec {

  describe("matchers are cool") {

    it("use range when asserting on a double") {
      10.01 should equal(10.00 +- 0.01) // this is from the scalactic library
    }

    it("check length syntax is easy to read") {
      "hello" should have length 5
    }

    it("checks the type of something") {
      new StringBuilder shouldBe a[StringBuilder]
    }

    it("has a good way of matching elements in a list") {
      List(1, 2, 3, 4) should contain(2)
      List(1, 2, 3, 4) should contain oneOf(4, 5, 6) //same as:  oneElementOf List(4, 5, 6)
      List(1, 2, 3, 4) should contain allOf(1, 4)
      List(1, 2, 3, 4) should contain atLeastOneOf(2, 3, 4)
      List(1, 2, 3, 4) should contain noneOf(5, 6, 7)
      List(1, 2, 3, 4) should contain only(3, 1, 2, 4)

      List(1, 2, 3, 4) should contain theSameElementsAs List(3, 1, 2, 4) // unordered

      // the next three examples do the same thing:
      List(1, 2) should contain theSameElementsInOrderAs List(1, 2)
      List(1, 2, 3, 4) should equal(List(1, 2, 3, 4))
      List(1, 2, 3, 4) should contain inOrderOnly(1, 2, 3, 4)

      List(1, 3, 10) shouldBe sorted
    }

    it("you can customize equality and enforce type constraints") {
      val hi = "Hi"
      hi should ===("Hi")
    }

    it("you can check properties of objects") {
      object table {
        val length = 10
        val width = 5
        val description = "dining room table"
      }


      table should have(
        'width (5),
        'length (10),
        'description ("dining room table")
      )
    }

    // these are implemented as macros:
    it("and my personal favorite: this code does not compile") {
      "val a: String = 1" shouldNot compile
      "val a: Int = 1" should compile
      "val a: String = 1" shouldNot typeCheck // should typeCheck doesn't appear to be available
    }
  }
}

