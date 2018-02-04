package net.qwerty2501.radoc
import io.circe._
import io.circe.syntax._
import io.circe.parser._
trait JsonHint {
  val parameterHint: ParameterHint
}

case class JsonObjectHint(parameterHint: ParameterHint, children: Seq[JsonHint])
    extends JsonHint

case class JsonArrayHint(parameterHint: ParameterHint, children: Seq[JsonHint])
    extends JsonHint

case class JsonValueHint(parameterHint: ParameterHint) extends JsonHint

object JsonHint {
  def apply[T](hint: T, descriptionMap: Map[String, Text])(
      implicit encoder: Encoder[T]): JsonHint = {
    val hintJson = hint.asJson(encoder)
    JsonObjectHint(ParameterHint(Parameter("", "", Text())), Seq())
  }
}
