package net.qwerty2501.radoc

import scala.xml._

trait Text {
  def renderHtml(args: HtmlRenderArguments): Node
}

private case class NodeText(node: Node) extends Text {
  override def renderHtml(args: HtmlRenderArguments): Node =
    node
}

private case class CustomText(handler: (HtmlRenderArguments) => Node)
    extends Text {
  override def renderHtml(args: HtmlRenderArguments): Node =
    handler(args)
}

private case class CombinedText(text1: Text, text2: Text) extends Text {
  override def renderHtml(args: HtmlRenderArguments): Node = {
    <div>
      <p>
        text1.renderHtml(args)
      </p>
      <p>
        text2.renderHtml(args)
      </p>
    </div>
  }
}

object Text {
  def apply(): Text = apply("")
  def apply(text: String): Text = NodeText(Unparsed(text))
  def apply(node: Node): Text = NodeText(node)
  def apply(handler: (HtmlRenderArguments) => Node): Text =
    CustomText(handler)

  private[radoc] def apply(text1: Text, text2: Text) =
    CombinedText(text1, text2)
}
