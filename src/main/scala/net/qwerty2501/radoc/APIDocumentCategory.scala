package net.qwerty2501.radoc

private case class APIDocumentCategory(
    category: String,
    requestResponseDocuments: Seq[APIDocumentGroup]) {

  def this(requestResponseDocuments: Seq[APIDocumentGroup]) =
    this("", requestResponseDocuments)
}
