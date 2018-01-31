package net.qwerty2501.radoc

case class Parameter(name: String,
                     value: Any,
                     typeName: String,
                     description: Text) {

  def this(name: String,
           value: Any,
           typeNames: Seq[String],
           description: Text) =
    this(name, value, typeNames.mkString(" | "), description)

  def this(name: String, value: Any, valueType: Class[_], description: Text) =
    this(name, value, valueType.toString, description)

  def this(name: String, value: Any, description: Text) =
    this(name, value, value.getClass, description)

}

object Parameter {
  def apply(name: String, value: Any, description: Text): Parameter =
    new Parameter(name, value, value.getClass, description)
  def apply(name: String,
            value: Any,
            valueType: Class[_],
            description: Text): Parameter =
    new Parameter(name, value, valueType, description)

  def apply(name: String,
            value: Any,
            typeNames: Seq[String],
            description: Text) =
    new Parameter(name, value, typeNames, description)
}
