package testingscala

class ArtistTest extends BaseSpec {
  describe("Artist") {

    it("full name correct with middle name") {
      val artist = new Artist("John", "Middleton", "Doe")
      artist.fullName shouldBe "John Middleton Doe"
    }

    it("full name correct with no middle name") {
      val artist = new Artist("John", "Doe")
      artist.fullName shouldBe "John Doe"
    }
  }
}
