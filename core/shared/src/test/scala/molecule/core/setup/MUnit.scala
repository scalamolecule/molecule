package molecule.core.setup

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import munit.FunSuite

trait MUnit extends FunSuite {

  override def munitTimeout: FiniteDuration = 3.minutes

  extension (s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  extension (lhs: Any)
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
}
