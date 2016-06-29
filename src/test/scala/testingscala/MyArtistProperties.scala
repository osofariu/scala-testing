package testingscala

import org.scalacheck.{Prop, Properties}

object MyArtistProperties extends Properties("MyArtist") {

  property("middleNames") =
    Prop.forAll {
      (firstName: String, middleName: Option[String], lastName: String) =>
        middleName match {
          case Some(x) =>
            val artist = new MyArtist(firstName, x, lastName)
            artist.fullName == firstName + " " + x + " " + lastName
          case _ =>
            val artist = new MyArtist(firstName, lastName)
            artist.fullName == firstName + " " + lastName
        }
    }
}
