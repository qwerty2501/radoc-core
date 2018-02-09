package net.qwerty2501.radoc

case class Version(prefix: String, major: Int, minor: Int, build: Int) {
  if (major < 0 || minor < 0 || build < 0) {
    throw new IllegalArgumentException()
  }

  def this(major: Int, minor: Int, build: Int) = this("", major, minor, build)
  def this(major: Int, minor: Int) = this(major, minor, 0)
  def this(major: Int) = this(major, 0)
  def this() = this(0)

  override def toString: String = mkString('.')

  def mkString(delimiter: Char): String =
    "" + major + delimiter + minor + delimiter + build
}

object Version {
  val firstVersion: Version = Version(0, 0, 1)
  val empty: Version = new Version()
  def apply(): Version = empty
  def apply(major: Int): Version = new Version(major)
  def apply(major: Int, minor: Int): Version = new Version(major, minor)
  def apply(major: Int, minor: Int, build: Int): Version =
    new Version(major, minor, build)

}
