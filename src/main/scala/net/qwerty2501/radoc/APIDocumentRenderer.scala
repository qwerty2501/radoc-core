package net.qwerty2501.radoc
import java.io._

import scala.xml._
import scala.io.Source
object APIDocumentRenderer {

  def renderHtmlTo(rootAPIDocument: RootAPIDocument, outputPath: String): Unit =
    renderHtmlTo(rootAPIDocument, outputPath, APIDocumentRendererContext())
  def renderHtmlTo(rootAPIDocument: RootAPIDocument,
                   outputPath: String,
                   context: APIDocumentRendererContext): Unit = {
    outputDocument(APIDocumentHtmlRenderer.render(rootAPIDocument, context), outputPath)
  }

  private[radoc] def outputDocument(document: String,
                                    outputPath: String): Unit = {
    val printWriter = new PrintWriter(new File(outputPath))
    printWriter.write(document)
    printWriter.close()

  }

}
