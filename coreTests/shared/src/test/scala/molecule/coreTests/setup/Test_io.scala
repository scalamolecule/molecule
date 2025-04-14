package molecule.coreTests.setup

import munit.CatsEffectSuite

trait Test_io extends CatsEffectSuite {

  implicit class TestableString(s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
  }
}
