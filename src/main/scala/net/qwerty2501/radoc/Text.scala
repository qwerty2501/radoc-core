package net.qwerty2501.radoc

import scala.xml._

trait Text {
  def render(messageDocument:MessageDocument,rootAPIDocument:RootAPIDocument): Node
}



private case class NodeText(node:Node)extends Text{
  override def render(messageDocument: MessageDocument, rootAPIDocument: RootAPIDocument): Node = node
}

private case class CustomText(handler:(MessageDocument,RootAPIDocument)=>Node) extends Text{
  override def render(messageDocument: MessageDocument, rootAPIDocument: RootAPIDocument): Node = handler(messageDocument,rootAPIDocument)
}


object Text{
  def apply(text:String) :Text = NodeText(xml.Text(text))
  def apply(node:Node):Text = NodeText(node)
  def apply(handler:(MessageDocument,RootAPIDocument)=>Node):Text = CustomText(handler)
}