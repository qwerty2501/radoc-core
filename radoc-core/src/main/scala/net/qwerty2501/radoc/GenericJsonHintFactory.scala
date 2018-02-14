package net.qwerty2501.radoc
import java.time._
import java.time.chrono.ChronoZonedDateTime
import scala.reflect.runtime.universe._
import scala.tools.reflect.ToolBox
import com.github.dwickern.macros.NameOf._

object GenericJsonHintFactory {

  private type JsonFieldHandler =
    (FieldName, Option[_], Type, FieldModifier) => JsonHint
  private case class FieldName(private val name: String) {
    def getName(fieldModifier: FieldModifier,
                fieldHintAnnotation: FieldHintAnnotation): String =
      if (fieldHintAnnotation.parameter.field != "")
        fieldHintAnnotation.parameter.field
      else fieldModifier.fieldModify(name)
  }
  private final val seqTypeName = classOf[Seq[_]].getName
  private val mirror = runtimeMirror(
    classOf[FieldHintAnnotation].getClassLoader)
  private val toolbox = mirror.mkToolBox()

  private val jsonValueTypes = Seq(
    typeOf[String],
    typeOf[ChronoZonedDateTime[LocalDate]]
  )

  def generate[T: TypeTag: NotNothing](
      defaultFieldModifier: FieldModifier): JsonHint = {
    new DefaultGenerator(defaultFieldModifier).generate(
      FieldName(""),
      Option.empty,
      typeOf[T],
      FieldHintAnnotation.default)
  }

  def generateExpected[T: TypeTag: NotNothing](
      expected: T,
      defaultFieldModifier: FieldModifier): JsonHint = ???

  private trait Generator {
    val defaultFieldModifier: FieldModifier
    val defaultAssertFactory: ParameterAssertFactory

    def generate(fieldName: FieldName,
                 value: Option[_],
                 t: Type,
                 fieldHintAnnotation: FieldHintAnnotation,
    ): JsonHint = {

      if (t.typeSymbol.fullName == seqTypeName) {
        generateArray(fieldName, value, t, fieldHintAnnotation)
      } else if (t.typeSymbol.asClass.isPrimitive || jsonValueTypes.exists(
                   jvt => t == jvt || t.baseClasses.exists(_ == jvt))) {
        generateValue(fieldName, value, t, fieldHintAnnotation)
      } else {
        generateObject(fieldName, value, t, fieldHintAnnotation)
      }

    }

    def generateArray(name: FieldName,
                      value: Option[_],
                      t: Type,
                      fieldHintAnnotation: FieldHintAnnotation): JsonHint
    def generateObject(name: FieldName,
                       value: Option[_],
                       t: Type,
                       fieldHintAnnotation: FieldHintAnnotation): JsonHint
    def generateValue(name: FieldName,
                      value: Option[_],
                      t: Type,
                      fieldHintAnnotation: FieldHintAnnotation): JsonHint

    def typeToClass(t: Type): Class[_] = rootMirror.runtimeClass(t)

    protected def getTypeName(t: Type): String =
      t.typeSymbol.asClass.name.toTypeName.toString

    def generateParameterHint(
        name: FieldName,
        value: Option[_],
        typeName: String,
        t: Type,
        fieldHintAnnotation: FieldHintAnnotation): ParameterHint =
      ParameterHint(
        Parameter(name.getName(defaultFieldModifier, fieldHintAnnotation),
                  value,
                  typeName,
                  fieldHintAnnotation.parameter.description),
        (if (fieldHintAnnotation.defaultParameterAssertFactory != ParameterAssertFactory.default)
           fieldHintAnnotation.defaultParameterAssertFactory
         else defaultAssertFactory)
          .generate(value, typeToClass(t)),
        fieldHintAnnotation.essentiality
      )

    def pickTypeName(typeName: String,
                     fieldHintAnnotation: FieldHintAnnotation): String =
      if (fieldHintAnnotation.parameter.typeName != "")
        fieldHintAnnotation.parameter.typeName
      else typeName
  }

  private class DefaultGenerator(
      override val defaultFieldModifier: FieldModifier)
      extends Generator {

    override val defaultAssertFactory: ParameterAssertFactory =
      ParameterAssertFactory.NoneAssertFactory

    def generateArray(name: FieldName,
                      value: Option[_],
                      t: Type,
                      fieldHintAnnotation: FieldHintAnnotation): JsonHint = {
      val typeArgName = t.typeArgs.headOption
        .fold("____unknown_generic_argument_type____")(getTypeName)
      val typeName = pickTypeName("[]" + typeArgName, fieldHintAnnotation)

      JsonArrayHint(
        generateParameterHint(name, value, typeName, t, fieldHintAnnotation),
        generate(FieldName(""),
                 Option.empty,
                 t.typeArgs.head,
                 FieldHintAnnotation.default),
        Seq()
      )
    }

    def generateObject(name: FieldName,
                       value: Option[_],
                       t: Type,
                       fieldHintAnnotation: FieldHintAnnotation): JsonHint = {

      JsonObjectHint(
        generateParameterHint(name,
                              value,
                              getTypeName(t),
                              t,
                              fieldHintAnnotation),
        t.members.collect {
          case m: MethodSymbol if m.isGetter && m.isPublic =>
            val faOption =
              m.annotations.find(_.tree.tpe =:= typeOf[FieldHintAnnotation])
            val fannotation = faOption.fold(FieldHintAnnotation.default) { p =>
              toolbox
                .eval(toolbox.untypecheck(p.tree))
                .asInstanceOf[FieldHintAnnotation]
            }

            if (fannotation.defaultParameterAssertFactory == ParameterAssertFactory.EqualAssertFactory) {
              throw new IllegalArgumentException(
                "invalid specify assert type. if you want assert equal value, use " + nameOf(
                  JsonBodyHint) + "." + nameOf(JsonBodyHint.expectedHint()))
            }

            generate(FieldName(m.name.toString),
                     Option.empty,
                     m.returnType,
                     fannotation)

        }.toSeq
      )
    }

    def generateValue(name: FieldName,
                      value: Option[_],
                      t: Type,
                      fieldHintAnnotation: FieldHintAnnotation): JsonHint =
      JsonValueHint(
        generateParameterHint(name,
                              value,
                              pickTypeName(getTypeName(t), fieldHintAnnotation),
                              t,
                              fieldHintAnnotation))
  }

}
