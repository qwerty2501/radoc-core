package net.qwerty2501.radoc

case class Request(method: Method,
                   path: UrlPath,
                   headerMap: HeaderMap,
                   content: Content)
    extends Message {

  def this(method: Method, path: UrlPath, headers: HeaderMap) =
    this(method, path, headers, Content())

  def this(method: Method, path: UrlPath, text: String) =
    this(method, path, Map[String, HeaderParameter](), Content(text))

  def this(method: Method, path: UrlPath) =
    this(method, path, Map[String, HeaderParameter](), Content())

  def this(method: Method, path: UrlPath, content: Content) =
    this(method, path, Map[String, HeaderParameter](), content)

  def this(method: Method, path: UrlPath, headers: HeaderMap, text: String) =
    this(method, path, headers, Content(text))

}

object Request {

  def get(path: UrlPath): Request = apply(Method.Get, path)

  def get(path: UrlPath, headers: HeaderMap) =
    apply(Method.Get, path, headers)

  def post(path: UrlPath, content: String) = apply(Method.Post, path, content)

  def post(path: UrlPath, content: Content) = apply(Method.Post, path, content)

  def post(path: UrlPath, headers: HeaderMap, content: String) =
    apply(Method.Post, path, headers, content)

  def put(path: UrlPath, content: String) = apply(Method.Put, path, content)

  def put(path: UrlPath, content: Content) = apply(Method.Put, path, content)

  def put(path: UrlPath, headers: HeaderMap, content: String) =
    apply(Method.Put, path, headers, content)

  def delete(path: UrlPath) = apply(Method.Delete, path)

  def delete(path: UrlPath, headers: HeaderMap) =
    apply(Method.Delete, path, headers)

  def delete(path: UrlPath, headers: HeaderMap, content: String) =
    apply(Method.Delete, path, headers, content)

  def delete(path: UrlPath, headers: HeaderMap, content: Content) =
    apply(Method.Delete, path, headers, content)

  def apply(method: Method, path: UrlPath, headers: HeaderMap) =
    new Request(method, path, headers)

  def apply(method: Method, path: UrlPath, text: String) =
    new Request(method, path, TextContent(text))

  def apply(method: Method, path: UrlPath) =
    new Request(method, path)

  def apply(method: Method, path: UrlPath, content: Content) =
    new Request(method, path, content)

  def apply(method: Method, path: UrlPath, headers: HeaderMap, text: String) =
    new Request(method, path, headers, TextContent(text))

}
