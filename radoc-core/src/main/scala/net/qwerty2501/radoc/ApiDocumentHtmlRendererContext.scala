package net.qwerty2501.radoc

case class ApiDocumentHtmlRendererContext(
    contentHtmlRenderer: ContentHtmlRenderer = ContentHtmlRenderer.default) {}

object ApiDocumentHtmlRendererContext {
  def apply(
      contentHtmlRenderer: ContentHtmlRenderer = ContentHtmlRenderer.default)
    : ApiDocumentHtmlRendererContext =
    new ApiDocumentHtmlRendererContext(contentHtmlRenderer)
}
