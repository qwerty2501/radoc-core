package net.qwerty2501.radoc

import scala.collection.mutable

case class APIDocumentWithVersion(documents: Map[Version, APIDocument]) {}

object APIDocumentWithVersion {
  def apply(): APIDocumentWithVersion =
    new APIDocumentWithVersion(Map())
}
