package net.qwerty2501.radoc

private sealed trait NotNothing[-T]

private object NotNothing {
  implicit object TypeArgumentShouldNotBeNothing extends NotNothing[Nothing]
  implicit object anyType extends NotNothing[Any]
}
