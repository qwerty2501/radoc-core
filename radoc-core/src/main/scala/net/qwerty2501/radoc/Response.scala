package net.qwerty2501.radoc

case class Response private (status: Status,
                             headers: HeaderParameterList,
                             body: Body)
    extends Message {

  def this(status: Status, headers: Seq[Parameter], body: Body) =
    this(status, HeaderParameterList(headers), body)
  def this(status: Status) = this(status, Seq(), Body())

}

object Response {

  def apply(status: Status): Response = new Response(status)
  def apply(status: Status, headers: Seq[Parameter], body: Body): Response =
    new Response(status, headers, body)

  private def apply(status: Status,
                    headers: HeaderParameterList,
                    body: Body): Response =
    new Response(status, headers, body)

}
