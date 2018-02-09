package net.qwerty2501.radoc

import scala.xml._
import io.circe._, io.circe.parser._
trait Body {
  def contentText: String
}

object Body {
  val empty: Body = TextBody("")
  def apply(): Body = empty
  def apply(text: String): Body = TextBody(text)

}

private case class TextBody(text: String) extends Body {
  override def contentText: String = text

}
