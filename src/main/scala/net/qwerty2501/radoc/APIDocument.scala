package net.qwerty2501.radoc

import scala.collection.mutable

class APIDocument private () {

  private val apis: mutable.Map[String, RequestResponseContainer] =
    mutable.Map()

  def append(request: Request, response: Response): Unit =
    append(RequestResponseDocument(request, response))

  def append(requestResponse: RequestResponseDocument): Unit =
    append(requestResponse.request.path, requestResponse)
  def append(category: String, requestResponse: RequestResponseDocument) =
    this.put(
      apis.getOrElse(category, RequestResponseContainer(category)) :+ requestResponse)

  def put(apiContainer: RequestResponseContainer) =
    apis.put(apiContainer.category, apiContainer)

}

object APIDocument {
  def apply(): APIDocument = new APIDocument()
}
