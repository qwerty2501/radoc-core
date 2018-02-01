package net.qwerty2501.radoc

import scala.xml._

trait Content {}

object Content {
  def apply(): Content = NothingContent()
  def apply(text: String): Content =
    if (text != "") TextContent(text) else NothingContent()
  def apply(element:Elem):Content = XMLContent(element)

  def apply(text:String,contentType:String):Content = if( contentType.contains("json") ){
    Content()
  } else if (contentType.contains("xml")){
    apply(xml.XML.loadString(text))
  } else {
    apply(text)
  }
}

private case class NothingContent() extends Content {
  override def toString: String = ""
}

private case class TextContent(text: String) extends Content {
  override def toString:String = text
}

private case class XMLContent(element:Elem) extends Content{
  override def toString: String =  Xhtml.toXhtml(element)
}