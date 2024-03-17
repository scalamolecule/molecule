package molecule.coreTests.setup

import molecule.core.util.Executor._
import scala.concurrent.Future


trait CoreTestSuite extends CoreTestSuiteBase {

  override val isJsPlatform: Boolean = false

  override def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }

  private def showResult(lhs: Any, rhs: Any): String = {
    s"""
       |Got     : $lhs
       |Expected: $rhs
       |""".stripMargin
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[V](rhs: V) = {
      (lhs, rhs) match {
        // Hack to make Arrays compare sanely; at some point we may want some
        // custom, extensible, typesafe equality check but for now this will do
        case (lhs: Array[_], rhs: Array[_]) =>
          Predef.assert(lhs.toSeq == rhs.toSeq,
            //            s"==> assertion failed: ${lhs.toSeq} != ${rhs.toSeq}"
            showResult(lhs.toSeq, rhs.toSeq)
          )
        case (lhs, rhs)                     =>
          Predef.assert(lhs == rhs,
            //            s"==> assertion failed: $lhs != $rhs"
            showResult(lhs, rhs)
          )
      }
    }
  }
}
