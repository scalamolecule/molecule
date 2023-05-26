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
}
