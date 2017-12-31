package net.qwerty2501.radoc

import java.io.File
import java.nio.file._

import org.scalatest._

import scala.io.Source

class APIDocumentGeneratorSpec extends FlatSpec with Matchers {

  it should "can generate api document" in {
    val rootAPIDocument = RootAPIDocument(Map())
    APIDocumentGeneratorInternal.generate(rootAPIDocument) should not be empty
  }

  it should "can output document" in {
    val text = "test out put"
    val dir = Files.createTempDirectory("sample")
    val outputPath = dir + "testFile"
    val path = Paths.get(outputPath)

    Files.deleteIfExists(path)
    APIDocumentGeneratorInternal.outputDocument(text, outputPath)
    Files.exists(path) should be(true)
    val source = Source.fromFile(outputPath)
    val actualText = new String(source.toArray)
    actualText should be(text)
  }
}
