package net.qwerty2501.radoc

import net.qwerty2501.radoc

private trait ContentType {
  def contentType(): String
}

private object ContentType {
  val None: ContentType = () => ""
  val Json: ContentType = () => "application/json"
  val Xml: ContentType = () => "application/xml"
  val Html: ContentType = () => "text/html"
  private[radoc] def apply(headers: HeaderParameterList): ContentType = {
    val contentType =
      headers.getHeaders.headOption
        .find(_.field == "Content-Type")
        .getOrElse(Parameter("Content-Type", "", radoc.Text()))
        .value
        .toString

    if (contentType.contains("json")) {
      Json
    } else if (contentType.contains("xml")) {
      Xml
    } else {
      Html
    }
  }

}
