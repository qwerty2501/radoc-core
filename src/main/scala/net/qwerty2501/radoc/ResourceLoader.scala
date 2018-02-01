package net.qwerty2501.radoc

import java.io.FileNotFoundException

import scala.io._
import scala.util.Try
import scala.xml._

private object ResourceLoader {
  private def basePath = "net.qwerty2501.radoc"

  private def assets = "assets"

  def loadCss(path: String): Node = loadTextAsNode(assets + "/css/" + path)

  def loadJavaScript(path: String): Node =
    loadTextAsNode(assets + "/js/" + path)

  private def loadTextAsNode(path: String): Node = Unparsed(loadText(path))

  private def loadText(path: String): String = load(path).getLines.mkString

  private def load(path: String): BufferedSource = {
    val targetPath = basePath + "/" + path
    Try(io.Source.fromResource(targetPath))
      .recover[BufferedSource](throw new FileNotFoundException(targetPath))
      .get
  }

}
