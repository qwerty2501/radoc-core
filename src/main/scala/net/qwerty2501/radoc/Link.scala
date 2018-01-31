package net.qwerty2501.radoc

object Link {

  def href(apiDocumentGroup: APIDocumentGroup): String = {

    href(apiDocumentGroup.version,
         apiDocumentGroup.category,
         apiDocumentGroup.group)

  }
  def href(apiDocument: APIDocument): String = {
    href(apiDocument.version, apiDocument.category, apiDocument.group) + "#" + fragment(
      apiDocument)
  }

  private def href(version: Version,
                   category: String,
                   group: String): String = {
    "?mainContentId=" + mainContentId(version) + "&contentId=" +
      templateId(category, group) + "&version=" + version.toString
  }

  def fragment(apiDocument: APIDocument): String =
    "#" + fragmentId(apiDocument)

  private[radoc] def mainContentId(version: Version): String =
    "main-content-" + version.toString.hashCode

  private[radoc] def templateId(category: String, group: String): String =
    category.hashCode.toString + group.hashCode.toString

  private[radoc] def fragmentId(apiDocument: APIDocument): String = {
    val group = apiDocument.group
    val category = apiDocument.category
    val version = apiDocument.version
    version.hashCode + category.hashCode.toString + group.hashCode.toString +
      apiDocument.method.name.hashCode.toString + apiDocument.path.displayPath.hashCode.toString
  }

}
