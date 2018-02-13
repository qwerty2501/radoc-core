package net.qwerty2501

package object radoc {
  private[radoc] implicit class ParameterValueOption(self: Option[_]) {
    def getOrNull: Any = self.getOrElse("null")

    def getValueString: String = getOrNull.toString
  }
}
