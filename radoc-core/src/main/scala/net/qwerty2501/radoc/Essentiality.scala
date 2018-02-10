package net.qwerty2501.radoc

final class Essentiality private () {}

object Essentiality {
  final val OmitEmpty: Essentiality = new Essentiality()
  final val Mandatory: Essentiality = new Essentiality()
  final val Excluded: Essentiality = new Essentiality()
}
