package net.qwerty2501.radoc

import org.scalatest._
import io.circe._
import io.circe.parser._

class JsonBodyHintMergerSpec extends FlatSpec with Matchers {

  private def json(jsonString: String): Json =
    parse(jsonString).getOrElse(Json.Null)

  private def merge(text: String, jsonBodyHint: JsonBodyHint) =
    new JsonBodyHintMerger().merge(
      json(text),
      jsonBodyHint
    )

  it should "can parse" in {

    val newHint = merge(
      """
       |{
       | "member1":33,
       | "member2":"test member"
       |}
     """.stripMargin,
      JsonBodyHint()
    )

    newHint.typeParameterMap.size should be(1)

    val parameters = newHint.typeParameterMap("Unknown Object 1")
    parameters.length should be(2)
    val firstParameter = parameters.head
    firstParameter.field should be("member1")
    firstParameter.typeName should be("Number")
    firstParameter.description should be(Text())

    val secondParameter = parameters(1)
    secondParameter.field should be("member2")
    secondParameter.typeName should be("String")
    secondParameter.description should be(Text())
  }

}
