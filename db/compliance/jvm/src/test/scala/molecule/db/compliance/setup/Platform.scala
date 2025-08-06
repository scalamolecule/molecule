package molecule.db.compliance.setup

import scala.concurrent.Future
import molecule.db.common.util.Executor.*


trait Platform {
  val platform: String = "jvm"

  def delay(ms: Int): Future[Unit] = Future {
    Thread.sleep(ms)
  }
}
