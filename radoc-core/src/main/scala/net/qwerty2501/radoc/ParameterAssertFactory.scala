package net.qwerty2501.radoc

trait ParameterAssertFactory {
  def generate(expected: Option[_]): ParameterAssert
}

object ParameterAssertFactory {

  private[radoc] val default: ParameterAssertFactory = (_) =>
    ParameterAssert.default

  val NoneAssertFactory: ParameterAssertFactory = (_) => ParameterAssert()
  val AssertEqualFactory: ParameterAssertFactory = (expected) =>
    ParameterAssert.assertEqual(expected)

  def customAssertEqualFactory[T >: Any: NotNothing](
      eq: (T, T) => Boolean): ParameterAssertFactory =
    (expected) => ParameterAssert.assertEqual(expected, eq)

  def customAssert(assertHandler: ((Option[_], Parameter) => Unit)) =
    ParameterAssert(assertHandler)
}
