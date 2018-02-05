package net.qwerty2501.radoc

case class Response private (status: Status,
                             headers: HeaderParameterList,
                             body: Body)
    extends Message {

  def this(status: Status,
           headers: Seq[Parameter] = Seq(),
           body: Body = Body()) =
    this(status, HeaderParameterList(headers), body)

}

object Response {

  def apply(status: Status,
            headers: Seq[Parameter] = Seq(),
            body: Body = Body()): Response =
    new Response(status, headers, body)

  private def apply(status: Status,
                    headers: HeaderParameterList,
                    body: Body): Response =
    new Response(status, headers, body)

}
