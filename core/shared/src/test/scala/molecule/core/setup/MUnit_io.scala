package molecule.core.setup

import munit.CatsEffectSuite

trait MUnit_io extends CatsEffectSuite {

  extension (s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  extension (lhs: Any) {
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
  }
}
