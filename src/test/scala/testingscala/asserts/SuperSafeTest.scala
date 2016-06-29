package testingscala.asserts

import testingscala.BaseSpec

class SuperSafeTest extends BaseSpec {

  describe("safe comparisons with SuperSafe") {

    it("Scala allows me to compare unrelated types") {
      assume(1 != Some(1)) // this passes, although Intellij does warn me, though.
    }

    it("but using SupeSafe it will be clear that I made a mistake") {
      // assume(1 !== Some(1))  // this does not allow me to compile my code!
    }
  }
}
