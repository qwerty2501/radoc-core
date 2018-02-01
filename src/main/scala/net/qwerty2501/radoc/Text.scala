package net.qwerty2501.radoc

import scala.xml._

trait Text {
  private[radoc] def render(args: TextRenderingArguments): Node
}

private case class NodeText(node: Node) extends Text {
  private[radoc] override def render(args: TextRenderingArguments): Node = node
}

private case class CustomText(handler: (TextRenderingArguments) => Node)
    extends Text {
  private[radoc] override def render(args: TextRenderingArguments): Node =
    handler(args)
}

object Text {
  def apply(): Text = apply("")
  def apply(text: String): Text = NodeText(xml.Text(text))
  def apply(node: Node): Text = NodeText(node)
  def apply(handler: (TextRenderingArguments) => Node): Text =
    CustomText(handler)
}

case class TextRenderingArguments(
    rootAPIDocument: RootAPIDocument,
    currentRootAPIDocumentWithVersion: RootAPIDocumentWithVersion,
    currentCategory: APIDocumentCategory,
    currentGroup: APIDocumentGroup,
    currentAPIDocument: APIDocument,
    currentMessageDocument: MessageDocument,
    context: APIDocumentRendererContext)
