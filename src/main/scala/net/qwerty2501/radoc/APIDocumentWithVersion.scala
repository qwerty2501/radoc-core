package net.qwerty2501.radoc

import scala.collection.mutable

case class APIDocumentWithVersion private (
    private var documents: mutable.Map[Version, APIDocument]) {
  def put(version: Version, apiDocument: APIDocument): Unit = {
    documents.put(version, apiDocument)
  }
}

object APIDocumentWithVersion {
  def apply(): APIDocumentWithVersion =
    new APIDocumentWithVersion(mutable.Map())
}
