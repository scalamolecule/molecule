package molecule.coreTests.setup

import zio.ZIO


trait CoreTestSuite_zio extends CoreTestSuiteBase_zio {

  override val isJsPlatform: Boolean = false

  override def delay[T](ms: Int)(body: => T): ZIO[Any, Nothing, T] = ZIO.succeed {
    Thread.sleep(ms)
    body
  }
}
