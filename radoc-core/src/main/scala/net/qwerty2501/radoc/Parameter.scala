package net.qwerty2501.radoc

import scala.reflect._

case class Parameter private (field: String,
                              value: Option[_],
                              typeName: String,
                              description: Text,
                              private[radoc] val color: Color) {

  def this(field: String,
           value: Option[_],
           typeName: String,
           description: Text) =
    this(field, value, typeName, description, ParameterColor.color())

  def this(field: String, value: Option[_], tte: Class[_], description: Text) =
    this(field, value, tte.getSimpleName, description)

}

object Parameter {

  private def apply(field: String,
                    value: Option[_],
                    typeName: String,
                    description: Text,
                    color: Color): Parameter =
    new Parameter(field, value, typeName, description)

  def apply(field: String,
            value: Option[_],
            typeName: String,
            description: Text): Parameter =
    new Parameter(field, value, typeName, description)

  def apply[T: ClassTag](field: String, value: T, description: Text)(
      implicit ct: ClassTag[T]): Parameter =
    new Parameter(field, Option(value), ct.runtimeClass, description)
  def apply[T: ClassTag](field: String, description: Text)(
      implicit ct: ClassTag[T]): Parameter =
    new Parameter(field, Option.empty, ct.runtimeClass, description)

  def apply(field: String,
            value: Option[_],
            tte: Class[_],
            description: Text): Parameter =
    new Parameter(field, value, tte, description)

}
