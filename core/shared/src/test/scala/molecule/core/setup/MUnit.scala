package molecule.core.setup

import munit.FunSuite
import scala.concurrent.duration.{DurationInt, FiniteDuration}

trait MUnit extends FunSuite {

  override def munitTimeout: FiniteDuration = 3.minutes

  implicit class TestableString(s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
  }
}
