package testingscala.styles

import org.scalatest.{Matchers, OneInstancePerTest, ParallelTestExecution}

import scala.collection.mutable

class WithFreeSpec extends org.scalatest.path.FreeSpec  with  Matchers {

  val myAttire = mutable.Set[String]()


  "My attire" - {

    "When starting out naked" - {
      "I should have nothing on" in {
        assert(myAttire.isEmpty)
      }
    }

    "Dressing up in the morning" - {
      "When putting on underwear" - {
        checkAddAttire("underwear", Array("underwear"))

        "When putting on socks" - {
          checkAddAttire("socks", Array("socks", "underwear"))

          "Dressing up for work" - {
            "When putting on nice pants" - {
              checkAddAttire("nice pants", Array("underwear", "socks", "nice pants"))

              "When putting on nice shirt" - {
                checkAddAttire("nice shirt", Array("underwear", "socks", "nice pants", "nice shirt"))

                "When putting on leather shoes" - {
                  checkAddAttire("leather shoes", Array("underwear", "socks", "nice pants", "nice shirt", "leather shoes"))
                }
              }
            }
          }

          "Dressing up for weekend" - {
            "When putting on t-shirt" - {
              checkAddAttire("t-shirt", Array("underwear", "socks", "t-shirt"))

              "When putting on shorts" - {
                checkAddAttire("shorts", Array("underwear", "socks", "t-shirt", "shorts"))

                "When putting on tennis shoes" - {
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

    s"I should have $itemsCount items on, consisting of: $itemsOn" in {
      assert(myAttire.contains(clothingItem))
      assert(myAttire.size == itemsOnMe.length)
      itemsOnMe.foreach(item => assert(myAttire.contains(item)))
    }
  }
}


