package molecule.db.compliance.setup

import molecule.db.common.util.Executor.*
import scala.concurrent.Future


trait Platform {
  val platform: String = "jvm"

  def delay(ms: Int): Future[Unit] = Future {
    Thread.sleep(ms)
  }
}
