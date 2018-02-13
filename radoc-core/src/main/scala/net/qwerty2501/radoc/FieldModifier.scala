package net.qwerty2501.radoc

trait FieldModifier {
  private[radoc] def fieldModify(field: String): String
}

object FieldModifier {

  private[radoc] val default: FieldModifier = _ => ""

  val None: FieldModifier = field => field

  val Snake: FieldModifier = field =>
    "[A-Z\\d]".r.replaceAllIn(field, { m =>
      "_" + m.group(0).toLowerCase()
    })

  val Camel: FieldModifier = field =>
    "_([a-z\\d])".r.replaceAllIn(field, { m =>
      m.group(1).toUpperCase
    })
}
