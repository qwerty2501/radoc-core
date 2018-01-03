package net.qwerty2501.radoc

case class DocumentArgs(category: String,
                        group: String,
                        description: String,
                        messageName: String,
                        version: Version) {
  def this(category: String, description: String, version: Version) =
    this(category, "", description, "", version)
}

object DocumentArgs {
  def apply(category: String, description: String, version: Version) =
    new DocumentArgs(category, description, version)
}
