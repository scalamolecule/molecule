package molecule.coreTests.setup

import zio._
import scala.concurrent.Promise
import scala.scalajs.js.timers.setTimeout
import scala.util.Try


trait CoreTestSuite_zio extends CoreTestSuiteBase_zio {

  override val isJsPlatform: Boolean = true

  override def delay[T](ms: Int)(body: => T): Task[T] = ZIO.fromFuture { _ =>
    val promise = Promise[T]()
    setTimeout(ms)(
      promise.complete(Try(body))
    )
    promise.future
  }
}
