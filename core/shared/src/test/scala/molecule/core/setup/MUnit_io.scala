package molecule.core.setup

import munit.CatsEffectSuite

trait MUnit_io extends CatsEffectSuite {

  implicit class TestableString(s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
  }
}
