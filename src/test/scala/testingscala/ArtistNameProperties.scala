package testingscala

import org.scalacheck.{Prop, Properties}

object ArtistNameProperties extends Properties("Artist") {
  property("middleNames") =
    Prop.forAll {
      (firstName: String, middleName: Option[String], lastName: String) =>
        middleName match {
          case Some(x) =>
            val artist = new Artist(firstName, x, lastName)
            artist.fullName == firstName + " " + x + " " + lastName
          case _ =>
            val artist = new Artist(firstName, lastName)
            artist.fullName == firstName + " " + lastName
        }
    }
}
