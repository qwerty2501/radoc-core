package net.qwerty2501.radoc

trait FieldModifier {
  private[radoc] def fieldModify(field: String): String
}

object FieldModifier {

  private[radoc] val default: FieldModifier = _ => ""

  val None: FieldModifier = field => field

  val Snake: FieldModifier = field =>
    field
      .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
      .replaceAll("([a-z\\d])([A-Z])", "$1_$2")
      .toLowerCase

}
