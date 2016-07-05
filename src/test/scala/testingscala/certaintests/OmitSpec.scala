package testingscala.certaintests

import testingscala.BaseSpec

class OmitSpec extends BaseSpec {

  describe("you can omit certain specs") {

    ignore("by ignoring them") {
      assert(Set.empty.nonEmpty)
    }

    it("or by pending them, if they are not yet implemented") {pending}
  }
}
