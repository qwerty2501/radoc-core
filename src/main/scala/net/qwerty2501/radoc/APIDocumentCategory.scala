package net.qwerty2501.radoc

private case class APIDocumentCategory(
    category: String,
    apiDocumentGroups: Map[String, APIDocumentGroup]) {}
