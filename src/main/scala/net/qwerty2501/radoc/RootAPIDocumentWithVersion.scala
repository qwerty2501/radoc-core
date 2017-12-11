package net.qwerty2501.radoc

private case class RootAPIDocumentWithVersion(
    version: Version,
    apiCategories: Seq[APIDocumentCategory])
