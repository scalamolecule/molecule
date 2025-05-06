package molecule.coreTests.setup

import scala.concurrent.{Future, Promise}
import scala.scalajs.js.timers.setTimeout
import scala.util.Try

trait Platform {
  val platform: String = "js"

  def delay(ms: Int): Future[Unit] = {
    val promise = Promise[Unit]()
    setTimeout(ms)(
      promise.complete(Try(()))
    )
    promise.future
  }
}
