package testingscala

case class MyArtist(firstName: String, middleName: Option[String], lastName: String, albums: Option[List[Album]]) {
  def this(firstName: String, lastName: String) = this(firstName, Option.empty, lastName, None)
  def this(firstName: String, middleName: String, lastName: String) = this(firstName, Some(middleName), lastName, None)

  def fullName = middleName match {
    case (None) =>
      firstName + " " + lastName
    case (Some(m)) =>
      firstName + " " + m + " " + lastName
  }

  def addAlbum(album: Album) : MyArtist = {
    val newAlbums = Option(album :: albums.get)
    new MyArtist(firstName, middleName, lastName, newAlbums)
  }
}


object MyArtist {
  def apply(firstName: String, middleName: String, lastName: String) : MyArtist = {
    new MyArtist(firstName, Some(middleName), lastName, None)
  }
  def apply(firstName: String, lastName: String): MyArtist = {
    new MyArtist(firstName, None, lastName, None)
  }
}
