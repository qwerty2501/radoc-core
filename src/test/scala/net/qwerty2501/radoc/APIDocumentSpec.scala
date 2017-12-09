package net.qwerty2501.radoc

import org.scalatest._

class APIDocumentSpec extends FlatSpec with Matchers {
  it should "can be create from request response pairs." in {
    val expectedPath = "/test/path"
    val expectedMethod = Methods.GET
    val expectedStatus = Statuses.OK
    val document = APIDocument.createFromRequestResponsePairs(
      Seq((Request.get(expectedPath) -> Response(expectedStatus))))

    document.apis.size should be(1)
    val requestResponseContainer = document.apis(expectedPath)
    requestResponseContainer.requestResponseDocuments.length should be(1)
    val requestResponses =
      requestResponseContainer.requestResponseDocuments.head.requestResponses
    requestResponses.length should be(1)
    val request = requestResponses.head._1
    val response = requestResponses.head._2
    request.path should be(expectedPath)
    request.method should be(expectedMethod)
    response.status should be(expectedStatus)

  }

  it should "be categorized by path and grouped by method and path" in {
    val category1Path = "category1/path"
    val expectedCategory1Group1Pairs =
      Seq((Request.get(category1Path), Response(Statuses.OK)),
          (Request.get(category1Path), Response(Statuses.OK)))
    val expectedContent1 = "expectedContent1"
    val expectedContent2 = "expectedContent2"
    val expectedCategory1Group2Pairs =
      Seq(
        (Request.post(category1Path, expectedContent1), Response(Statuses.OK)),
        (Request.post(category1Path, expectedContent2), Response(Statuses.OK))
      )
    val category2Path = "category2/path"
    val expectedCategory2Group1Pairs =
      Seq((Request.get(category2Path), Response(Statuses.OK)),
          (Request.get(category2Path), Response(Statuses.OK)))

    val document = APIDocument.createFromRequestResponsePairs(
      expectedCategory1Group1Pairs ++ expectedCategory1Group2Pairs ++ expectedCategory2Group1Pairs)

    val category1 = document.apis(category1Path)
    category1.requestResponseDocuments.size should be(2)
    val category1Group1 = category1.requestResponseDocuments
      .filter(_.requestResponses.head._1.method == Methods.GET)
      .head
    category1Group1.requestResponses
      .filter(_._1.method == Methods.GET)
      .filter(_._1.path == category1Path)
      .size should be(2)

    val category1Group2 = category1.requestResponseDocuments
      .filter(_.requestResponses.head._1.method == Methods.POST)
      .head

    category1Group2.requestResponses
      .filter(_._1.method == Methods.POST)
      .filter(_._1.path == category1Path)
      .size should be(2)

    val category2 = document.apis(category2Path)

    category2.requestResponseDocuments.size should be(1)

    category2.requestResponseDocuments.head.requestResponses
      .filter(_._1.method == Methods.GET)
      .filter(_._1.path == category2Path)
      .size should be(2)
  }

}
