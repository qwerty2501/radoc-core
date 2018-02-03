package net.qwerty2501.radoc

case class CaseType private ()

object CaseType {
  val None = CaseType()
  val Snake = CaseType()
  val Camel = CaseType()
}
