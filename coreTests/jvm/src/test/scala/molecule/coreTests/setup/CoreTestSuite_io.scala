package molecule.coreTests.setup

import cats.effect.IO


trait CoreTestSuite_io extends CoreTestSuiteBase_io {

  override val isJsPlatform: Boolean = false

  override def delay[T](ms: Int)(body: => T): IO[T] = IO {
    Thread.sleep(ms)
    body
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[T](rhs: T): Unit = {
      (lhs, rhs) match {
        case (lhs, rhs) => assertEquals(lhs, rhs)
      }
    }
  }
}
