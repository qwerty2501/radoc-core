package net.qwerty2501.radoc

private case class RootAPIDocumentWithVersion(
    version: Version,
    apiCategories: Map[String, APIDocumentCategory])
