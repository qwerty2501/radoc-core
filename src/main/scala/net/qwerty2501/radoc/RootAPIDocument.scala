package net.qwerty2501.radoc

private case class RootAPIDocument(
    documents: Map[Version, RootAPIDocumentWithVersion])
