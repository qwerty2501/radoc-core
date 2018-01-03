package net.qwerty2501.radoc

case class Response(status: Status, headers: HeaderMap, content: Content) {
  def this(status: Status) =
    this(status, HeaderMap(), NothingContent())

  def this(status: Status, headers: HeaderMap) =
    this(status, headers, NothingContent())

  def this(status: Status, text: String) =
    this(status, HeaderMap(), TextContent(text))

  def this(status: Status, content: Content) =
    this(status, HeaderMap(), content)
  def this(status: Status, headers: HeaderMap, text: String) =
    this(status, headers, TextContent(text))

}

object Response {
  def apply(status: Status): Response =
    new Response(status)
  def apply(status: Status, headers: HeaderMap): Response =
    new Response(status, headers)
  def apply(status: Status, text: String): Response =
    new Response(status, text)
  def apply(status: Status, content: Content): Response =
    new Response(status, content)
  def apply(status: Status, headers: HeaderMap, text: String): Response =
    new Response(status, headers, text)
}
