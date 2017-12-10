package net.qwerty2501.radoc

case class Status(statusCode: Int) {

  override def toString = "Status:" + statusCode

}

object Status {
  final val OK = Status(200)
}
