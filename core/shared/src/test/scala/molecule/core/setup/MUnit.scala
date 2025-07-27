package molecule.core.setup

import munit.FunSuite
import scala.concurrent.duration.{DurationInt, FiniteDuration}

trait MUnit extends FunSuite {

  override def munitTimeout: FiniteDuration = 3.minutes

  extension (s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  extension (lhs: Any)
    def ==>[V](rhs: V): Unit = assertEquals(lhs, rhs)
}
