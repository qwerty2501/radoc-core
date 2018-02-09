package net.qwerty2501.radoc

case class DocumentArgs(category: String,
                        group: String,
                        description: Text,
                        messageName: String,
                        version: Version) {
  def this() = this("", "", Text(), "", Version.empty)
  def this(description: Text) =
    this("", "", description, "", Version.empty)
  def this(category: String, description: Text, version: Version) =
    this(category, "", description, "", version)
}

object DocumentArgs {
  val empty = new DocumentArgs()
  def apply(): DocumentArgs = empty
  def apply(description: Text): DocumentArgs = new DocumentArgs(description)
  def apply(category: String, description: Text, version: Version) =
    new DocumentArgs(category, description, version)
}
