package net.qwerty2501.radoc

case class ParameterHint(parameter: Parameter,
                         assert: ParameterAssert,
                         essentiality: Essentiality) {

  def this(field: String,
           valueTypeName: String,
           description: Text,
           essentiality: Essentiality) =
    this(Parameter(field, Option.empty, valueTypeName, description),
         ParameterAssert(),
         essentiality)

  def this(field: String, valueTypeName: String, description: Text) =
    this(field, valueTypeName, description, Essentiality.Mandatory)
  def this(parameter: Parameter, essentiality: Essentiality) =
    this(parameter, ParameterAssert(), essentiality)

  def this(parameter: Parameter) =
    this(parameter, ParameterAssert(), Essentiality.Mandatory)

  def field: String = parameter.field
  def typeName: String = parameter.typeName
  def value: Option[_] = parameter.value
  def description: Text = parameter.description
}

object ParameterHint {

  def apply(field: String,
            valueTypeName: String,
            description: Text,
            essentiality: Essentiality): ParameterHint =
    new ParameterHint(field, valueTypeName, description, essentiality)

  def apply(field: String,
            valueTypeName: String,
            description: Text): ParameterHint =
    new ParameterHint(field, valueTypeName, description, Essentiality.Mandatory)

  def apply(parameter: Parameter, essentiality: Essentiality): ParameterHint =
    new ParameterHint(parameter, essentiality)

  def apply(parameter: Parameter) =
    new ParameterHint(parameter, Essentiality.Mandatory)

  def withEqualAssert(field: String,
                      expected: Option[_],
                      typeName: String,
                      description: Text,
                      essentiality: Essentiality): ParameterHint =
    ParameterHint(
      Parameter(field, expected, typeName, description),
      ParameterAssert.assertEqual(expected),
      essentiality
    )

  def withEqualAssert(field: String,
                      expected: Option[Option[_]],
                      typeName: String,
                      description: Text): ParameterHint =
    withEqualAssert(field,
                    expected,
                    typeName,
                    description,
                    Essentiality.Mandatory)

}
