package testingscala.styles

import org.scalatest.Spec


class WithSpec extends Spec {

    object `A Set` {
      object `when empty` {
        def `should have size 0` {
          assert(Set.empty.isEmpty)
        }

        def `should produce NoSuchElementException when head is invoked` {
          intercept[NoSuchElementException] {
            Set.empty.head
          }
        }
      }
    }

  }
