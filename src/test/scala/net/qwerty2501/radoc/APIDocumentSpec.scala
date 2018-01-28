package net.qwerty2501.radoc

import org.scalatest._

class APIDocumentSpec extends FlatSpec with Matchers {

  "unmatched path documents" should "throw IllegalArgumentException" in {
    intercept[IllegalArgumentException] {
      APIDocument(
        Method.Get,
        URLPath / "test/path1",
        Seq(MessageDocument(Request.get(URLPath / "test/path1"),
                            Response(Status.Ok)),
            MessageDocument(Request.get(URLPath / "test/path2"),
                            Response(Status.Ok))),
        Text("description")
      )
    }
  }

  "unmatched method documents" should "throw IllegalArgumentException" in {
    intercept[IllegalArgumentException] {
      APIDocument(
        Method.Get,
        URLPath / "test/path",
        Seq(
          MessageDocument(Request.post(URLPath / "test/path", Content()),
                          Response(Status.Ok)),
          MessageDocument(Request.get(URLPath / "test/path"),
                          Response(Status.Ok))
        ),
        Text("description")
      )
    }
  }
}
