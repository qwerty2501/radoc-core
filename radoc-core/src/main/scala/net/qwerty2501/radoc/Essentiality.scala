package net.qwerty2501.radoc

final class Essentiality private () {}

final class Test3 {}

object Essentiality {
  final val OmitEmpty: Essentiality = new Essentiality()
  final val Mandatory: Essentiality = new Essentiality()
}
