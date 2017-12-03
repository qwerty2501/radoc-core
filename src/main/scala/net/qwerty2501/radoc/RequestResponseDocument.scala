package net.qwerty2501.radoc

case class RequestResponseDocument private(id: String,
                                           title: String,
                                           request: Request,
                                           response: Response)

object RequestResponseDocument {
  def apply(request: Request, response: Response): RequestResponseDocument =
    RequestResponseDocument("", request, response)
  def apply(title: String, request: Request, response: Response): RequestResponseDocument =
    new RequestResponseDocument("", title, request, response)

}
