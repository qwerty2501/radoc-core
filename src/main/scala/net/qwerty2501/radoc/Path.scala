package net.qwerty2501.radoc



trait Path {

  val displayPath: String
  val actualPath: String

  override def equals(o: scala.Any): Boolean =
    o.isInstanceOf[Path] && this.displayPath == o.asInstanceOf[Path].displayPath
}

object Path {
  def apply(path: String): Path = AbsolutePath(path)
  def apply(path: String, params: Any*): Path = FormattedPath(path, params: _*)
}
