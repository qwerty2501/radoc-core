package net.qwerty2501.radoc

case class APIDocumentRendererContext(
    rootAPIDocumentTemplatePath: String,
    rootAPIDocumentWithVersionTemplatePath: String) {
  def this() =
    this("net.qwerty2501.radoc/rootAPIDocument.ssp",
         "rootAPIDocumentWithVersion.ssp")
}

object APIDocumentRendererContext {
  def apply() = new APIDocumentRendererContext()
}
