package molecule.coreTests.setup

import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.Try


trait CoreTestSuite extends CoreTestSuiteBase {

  override val isJsPlatform: Boolean = true

  override def delay[T](ms: Int)(body: => T): Future[T] = {
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
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
          Predef.assert(lhs.toSeq == rhs.toSeq, showResult(lhs.toSeq, rhs.toSeq))

        case (lhs, rhs) =>
          Predef.assert(lhs == rhs, showResult(lhs, rhs))
      }
    }
  }
}
