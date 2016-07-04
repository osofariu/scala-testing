package testingscala.styles

import org.scalatest.Matchers

import scala.collection.mutable

// DO THIS:
// remove ".path" below, to see how FunSpec is different from path.FunSpec:

class WithFunSpec extends org.scalatest.path.FunSpec with Matchers {

  val myAttire = mutable.Set[String]()


  describe("My attire") {

    describe("When starting out naked") {
      it("I should have nothing on") {
        assert(myAttire.isEmpty)
      }
    }

    describe("Dressing up in the morning") {
      describe("When putting on underwear") {
        checkAddAttire("underwear", Array("underwear"))

        describe("When putting on socks") {
          checkAddAttire("socks", Array("socks", "underwear"))

          describe("Dressing up for work") {
            describe("When putting on nice pants") {
              checkAddAttire("nice pants", Array("underwear", "socks", "nice pants"))

              describe("When putting on nice shirt") {
                checkAddAttire("nice shirt", Array("underwear", "socks", "nice pants", "nice shirt"))

                describe("When putting on leather shoes") {
                  checkAddAttire("leather shoes", Array("underwear", "socks", "nice pants", "nice shirt", "leather shoes"))
                }
              }
            }
          }

          describe("Dressing up for weekend") {
            describe("When putting on t-shirt") {
              checkAddAttire("t-shirt", Array("underwear", "socks", "t-shirt"))

              describe("When putting on shorts") {
                checkAddAttire("shorts", Array("underwear", "socks", "t-shirt", "shorts"))

                describe("When putting on tennis shoes") {
                  checkAddAttire("tennis shoes", Array("underwear", "socks", "t-shirt", "shorts", "tennis shoes"))
                }
              }
            }
          }
        }
      }
    }
  }

  def checkAddAttire(clothingItem: String, itemsOnMe: Array[String]) = {
    myAttire add clothingItem
    val itemsOn = itemsOnMe.mkString(",")
    val itemsCount = itemsOnMe.length

    it(s"I should have $itemsCount items on, consisting of: $itemsOn") {
      assert(myAttire.contains(clothingItem))
      assert(myAttire.size == itemsOnMe.length)
      itemsOnMe.foreach(item => assert(myAttire.contains(item)))
    }
  }
}