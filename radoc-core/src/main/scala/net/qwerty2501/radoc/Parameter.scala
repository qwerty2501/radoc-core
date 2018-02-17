package net.qwerty2501.radoc

class Parameter private (val field: String,
                         val value: Option[_],
                         val typeName: String,
                         val description: Text) {

  def this(field: String, value: Any, typeName: String, description: Text) =
    this(field, Option(value), typeName, description)

  def this(field: String, value: Option[_], description: Text) =
    this(field,
         value,
         value.fold("Nothing")(_.getClass.getSimpleName),
         description)

  def this(field: String, value: Any, description: Text) =
    this(field, Option(value), description)

  def this(field: String, typeClass: Class[_], description: Text) =
    this(field, Option.empty, typeClass.getSimpleName, description)
  lazy val color: Color = ParameterColor.color
}

object Parameter {

  def apply(field: String,
            value: Any,
            typeName: String,
            description: Text): Parameter =
    new Parameter(field, value, typeName, description)

  def apply(field: String,
            value: Option[_],
            typeName: String,
            description: Text): Parameter =
    new Parameter(field, value, typeName, description)

  def apply(field: String, value: Option[_], description: Text): Parameter =
    new Parameter(field, value, description)

  def apply(field: String, value: Any, description: Text): Parameter =
    new Parameter(field, value, description)
  def apply(field: String, typeClass: Class[_], description: Text): Parameter =
    new Parameter(field, typeClass, description)

}
