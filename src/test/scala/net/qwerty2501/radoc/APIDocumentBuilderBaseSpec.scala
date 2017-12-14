package net.qwerty2501.radoc

import net.qwerty2501.radoc.mocks._
import org.scalatest._

class APIDocumentBuilderBaseSpec extends FlatSpec with Matchers {

  it should "can add request response" in {
    val apiDocumentBuilder = new APIDocumentBuilderMock()
    val path = "test/path"
    val response = apiDocumentBuilder.request(Request.get(path))

    val messageDocument = apiDocumentBuilder.getRootAPIDocument
      .documents(Version())
      .apiCategories("")
      .apiDocumentGroups(path)
      .apiDocuments(Method.Get, path)
      .messageDocuments
      .head

    messageDocument.request.path.displayPath should be(path)
    messageDocument.response should be(response)
  }
}
