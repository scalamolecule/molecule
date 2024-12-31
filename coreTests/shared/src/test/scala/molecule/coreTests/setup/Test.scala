package molecule.coreTests.setup

import munit.FunSuite
import scala.concurrent.{Future, Promise}
import scala.util.Try

trait Test extends FunSuite {

  implicit class TestableString(s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
  }
}
