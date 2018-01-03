package net.qwerty2501.radoc

case class APIDocumentGenerateContext(templatePath: String) {
  def this() = this("net.qwerty2501.radoc/rootAPIDocument.ssp")
}

object APIDocumentGenerateContext {
  def apply() = new APIDocumentGenerateContext()
}
