package testingscala.scalacheck

object StringUtils {
  def contains(s: String, s2: String): Boolean = s.contains(s2)

  def tokenize(str: String, d: Char): List[String] = {
    if (str.isEmpty) return List[String]()
    str.split(d.toString).toList
  }

  def truncate(s: String, n: Int): String = {
    val l = s.length
    if (n < 0 || s.length <= n) return s
    s.substring(0,n)
  }

  def sternTruncate(s: String, n: Int) : String = {
    if (n < 0) {
      throw new IllegalArgumentException(s"You can't truncate with a negative index: $s")
    }
    truncate(s, n)
  }
}
