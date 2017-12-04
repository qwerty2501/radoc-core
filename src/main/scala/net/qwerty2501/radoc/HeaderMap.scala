package net.qwerty2501.radoc

import scala.collection.immutable.HashMap

trait HeaderMap extends Map[String, String]

object HeaderMap {
  def apply(): HeaderMap = new HeaderMapImpl()
}

private class HeaderMapImpl extends HashMap[String, String] with HeaderMap {}
