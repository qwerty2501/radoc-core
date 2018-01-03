package net.qwerty2501.radoc
import java.io._

import scala.xml._
object APIDocumentRenderer {

  def renderTo(rootAPIDocument: RootAPIDocument, outputPath: String): Unit =
    renderTo(rootAPIDocument, outputPath, APIDocumentRendererContext())
  def renderTo(rootAPIDocument: RootAPIDocument,
               outputPath: String,
               context: APIDocumentRendererContext): Unit = {
    APIDocumentRendererInternal.outputDocument(
      APIDocumentRendererInternal.render(rootAPIDocument, context),
      outputPath)
  }

}

private object APIDocumentRendererInternal {
  def outputDocument(document: String, outputPath: String): Unit = {
    val printWriter = new PrintWriter(new File(outputPath))
    printWriter.write(document)
    printWriter.close()

  }

  def render(rootAPIDocument: RootAPIDocument,
             context: APIDocumentRendererContext): String = {
    "<!DOCTYPE html>\n" +
      new PrettyPrinter(80, 2)
        .format(renderRootAPIDocument(rootAPIDocument, context))

  }

  def renderRootAPIDocument(rootAPIDocument: RootAPIDocument,
                            context: APIDocumentRendererContext): Elem = {
    <html>
        <head>
          <meta charset="UTF-8"/>
          <title>{rootAPIDocument.title}</title>
          <style>
            function displayTargetTo(parentId,targetId){{
            var template = document.getElementById(targetId);
            var targetElement = document.importNode(template.content,true);
            var parent = document.getElementById(parentId);
            parent.textContent = null;
            parent.appendChild(targetElement);
            }}

          </style>
        </head>
        <body>
          {
            if (rootAPIDocument.documents.size == 1 && rootAPIDocument.documents.head._1 == Version()) {
              renderRootAPIDocumentWithVersion(rootAPIDocument.documents.head._2,context)
            } else if (rootAPIDocument.documents.size > 1) {

            }
          }
        </body>
    </html>
  }

  def renderRootAPIDocumentWithVersion(
      rootAPIDocumentWithVersion: RootAPIDocumentWithVersion,
      context: APIDocumentRendererContext): Elem = {

    val apiCategories = rootAPIDocumentWithVersion.apiCategories
    val categories = apiCategories.keys

    def renderGroupHeaders(groups: Seq[String]): Seq[Elem] = {
      groups.map { group =>
        <li><a href="">{group}</a></li>
      }
    }

    <ul>

      {if (apiCategories.exists(_._1 == "")) {
      renderGroupHeaders(apiCategories.head._2.apiDocumentGroups.keys.toSeq)
    }}
      {
      for (tAPICategory <- apiCategories.filter(_._1 != "")){
        <p>{tAPICategory._1}</p>
        renderGroupHeaders(tAPICategory._2.apiDocumentGroups.keys.toSeq)
      }

      }
        
    </ul>
  }
}
