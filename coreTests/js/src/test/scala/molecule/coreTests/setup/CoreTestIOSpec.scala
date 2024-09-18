package molecule.coreTests.setup

import cats.effect.IO
import scala.concurrent.Promise
import scala.scalajs.js.timers.setTimeout
import scala.util.Try


trait CoreTestIOSpec extends CoreTestIOSuiteBase {

  override val isJsPlatform: Boolean = true

  override def delay[T](ms: Int)(body: => T): IO[T] = IO.fromFuture {
    IO {
      val promise = Promise[T]()
      setTimeout(ms)(
        promise.complete(Try(body))
      )
      promise.future
    }
  }

  implicit class ArrowAssert(lhs: Any) {
    def ==>[T](rhs: T): Unit = {
      (lhs, rhs) match {
        case (lhs, rhs) => assertEquals(lhs, rhs)
      }
    }
  }
}
