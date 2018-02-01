package net.qwerty2501.radoc

import net.qwerty2501.radoc

private final class ContentType private ()

private object ContentType {
  val Json = new ContentType()
  val Xml = new ContentType()
  val Text = new ContentType()
  private[radoc] def apply(headerMap: HeaderMap): ContentType = {
    val contentType =
      headerMap
        .getOrElse("Content-Type", HeaderParameter("", radoc.Text()))
        .value
        .toString

    if (contentType.contains("json")) {
      Json
    } else if (contentType.contains("xml")) {
      Xml
    } else {
      Text
    }
  }

}
