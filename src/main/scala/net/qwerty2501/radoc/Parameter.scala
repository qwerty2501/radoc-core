package net.qwerty2501.radoc

case class Parameter(name: String,
                     value: Any,
                     typeName: String,
                     description: Text)

object Parameter {
  def apply(name: String, value: Any, description: Text): Parameter =
    Parameter(name, value, value.getClass, description)
  def apply[T](name: String,
               value: Any,
               valueType: Class[T],
               description: Text): Parameter =
    Parameter(name, value, valueType.getSimpleName, description)
}
