package net.qwerty2501.radoc

import scala.annotation.StaticAnnotation

case class RequestAnnotation(method: Method,
                             requestHeaders: Seq[Parameter],
                             requestContentType: ContentType,
                             fieldModifier: FieldModifier)
    extends StaticAnnotation {
  def this(method: Method, requestHeaders: Seq[Parameter]) =
    this(method, requestHeaders, ContentType.None, FieldModifier.default)

  def this(method: Method) = this(method, Seq())
}

case class ResponseAnnotation(responseHeaders: Seq[Parameter],
                              responseContentType: ContentType,
                              fieldModifier: FieldModifier)
    extends StaticAnnotation {
  def this(responseHeaders: Seq[Parameter]) =
    this(responseHeaders, ContentType.None, FieldModifier.default)

}

case class FieldAnnotation(parameterHint: ParameterHint)
    extends StaticAnnotation
