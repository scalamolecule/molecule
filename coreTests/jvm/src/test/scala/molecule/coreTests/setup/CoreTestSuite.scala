package molecule.coreTests.setup

import molecule.core.util.Executor._
import scala.concurrent.Future


trait CoreTestSuite extends CoreTestSuiteBase {

  override val isJsPlatform: Boolean = false

  override def delay[T](ms: Int)(body: => T): Future[T] = Future {
    Thread.sleep(ms)
    body
  }
}
