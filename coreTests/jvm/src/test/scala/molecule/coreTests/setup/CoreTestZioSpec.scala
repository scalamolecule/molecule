package molecule.coreTests.setup

import zio.ZIO


trait CoreTestZioSpec extends CoreTestZioSpecBase {

  override val isJsPlatform: Boolean = false

  override def delay[T](ms: Int)(body: => T): ZIO[Any, Nothing, T] = ZIO.succeed {
    Thread.sleep(ms)
    body
  }
}
