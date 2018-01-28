package net.qwerty2501.radoc

case class APIDocumentGroup(group: String,
                            apiDocuments: Map[(Method, String), APIDocument],
                            category: String,
                            version: Version) {

  override def toString: String = group
}
