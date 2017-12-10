package net.qwerty2501.radoc

trait Path {

  val displayPath: String
  val actualPath: String

  override def hashCode() = displayPath.hashCode
  override def equals(o: Any) = {
    o match {
      case path: Path => path.displayPath == this.actualPath
      case _          => false
    }

  }

  override def toString = displayPath
}

object Path {
  def apply(path: String): Path = AbsolutePath(path)
  def apply(path: String, params: Any*): Path = FormattedPath(path, params: _*)
}
