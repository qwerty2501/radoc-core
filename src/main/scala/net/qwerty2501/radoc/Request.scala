package net.qwerty2501.radoc

import com.sun.net.httpserver.Headers

import scala.tools.nsc.io.Path

case class Request(method: Method,
                   path: String,
                   headers: HeaderMap,
                   content: Content) {

  def this(method: Method, path: String, headers: HeaderMap) =
    this(method, path, headers, NothingContent())
  def this(method: Method, path: String, text: String) =
    this(method, path, HeaderMap(), TextContent(text))
  def this(method: Method, path: String) =
    this(method, path, HeaderMap(), NothingContent())
  def this(method: Method, path: String, content: Content) =
    this(method, path, HeaderMap(), content)
  def this(method: Method, path: String, headers: HeaderMap, text: String) =
    this(method, path, headers, TextContent(text))
}

object Request {

  def get(path: String): Request = apply(Methods.GET, path)
  def get(path: String, headers: HeaderMap) = apply(Methods.GET, path, headers)
  def post(path: String, content: String) = apply(Methods.POST, path, content)
  def post(path: String, headers: HeaderMap, content: String) =
    apply(Methods.POST, path, headers, content)
  def put(path: String, content: String) = apply(Methods.PUT, path, content)
  def put(path: String, headers: HeaderMap, content: String) =
    apply(Methods.PUT, path, headers, content)

  def delete(path: String) = apply(Methods.DELETE, path)
  def delete(path: String, headers: HeaderMap) =
    apply(Methods.DELETE, path, headers)
  def delete(path: String, headers: HeaderMap, content: String) =
    apply(Methods.DELETE, path, headers, content)

  def apply(method: Method, path: String, headers: HeaderMap) =
    new Request(method, path, headers)
  def apply(method: Method, path: String, text: String): Request =
    new Request(method, path, TextContent(text))
  def apply(method: Method, path: String): Request =
    new Request(method, path)
  def apply(method: Method, path: String, content: Content): Request =
    new Request(method, path, content)
  def apply(method: Method,
            path: String,
            headers: HeaderMap,
            text: String): Request =
    new Request(method, path, headers, TextContent(text))
}
