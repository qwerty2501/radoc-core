package net.qwerty2501.radoc

case class RootAPIDocument(title: String,
                           documents: Map[Version, RootAPIDocumentWithVersion])
