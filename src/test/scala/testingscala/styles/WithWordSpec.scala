  import org.scalatest.WordSpec

  class WithWordSpec extends WordSpec {

    "A Set" when {
      "empty" should {
        "have size 0" in {
          assert(Set.empty.isEmpty)
        }

        "produce NoSuchElementException when head is invoked" in {
          intercept[NoSuchElementException] {
            Set.empty.head
          }
        }
      }
    }
  }
