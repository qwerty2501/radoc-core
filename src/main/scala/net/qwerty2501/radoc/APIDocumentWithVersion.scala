package net.qwerty2501.radoc

case class APIDocumentWithVersion(documents: Map[Version, APIDocument]) {
  def this() = this(Map())
}

object APIDocumentWithVersion {
  def apply(): APIDocumentWithVersion = new APIDocumentWithVersion()
}
