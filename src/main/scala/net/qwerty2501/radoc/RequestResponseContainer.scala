package net.qwerty2501.radoc

case class RequestResponseContainer private(id: String,
                                            category: String,
                                            getAPIs: Seq[RequestResponseDocument],
                                            postAPIs: Seq[RequestResponseDocument],
                                            putAPIs: Seq[RequestResponseDocument],
                                            deleteAPIs: Seq[RequestResponseDocument]) {

  def :+(api: RequestResponseDocument): RequestResponseContainer = this :+ (api.request.method, api)
  def :+(method: Method, api: RequestResponseDocument): RequestResponseContainer = {

    method match {
      case Methods.GET =>
        new RequestResponseContainer(this.id,
                         this.category,
                         this.getAPIs :+ api,
                         this.postAPIs,
                         this.putAPIs,
                         this.deleteAPIs)

      case Methods.POST =>
        new RequestResponseContainer(this.id,
                         this.category,
                         this.getAPIs,
                         this.postAPIs :+ api,
                         this.putAPIs,
                         this.deleteAPIs)

      case Methods.PUT =>
        new RequestResponseContainer(this.id,
                         this.category,
                         this.getAPIs,
                         this.postAPIs,
                         this.putAPIs :+ api,
                         this.deleteAPIs)

      case Methods.DELETE =>
        new RequestResponseContainer(this.id,
                         this.category,
                         this.getAPIs,
                         this.postAPIs,
                         this.putAPIs,
                         this.deleteAPIs :+ api)

    }
  }

}

object RequestResponseContainer {

  def apply(id: String, category: String): RequestResponseContainer =
    new RequestResponseContainer(id, category, Seq(), Seq(), Seq(), Seq())

  def apply(category: String): RequestResponseContainer = RequestResponseContainer("", category)
}
