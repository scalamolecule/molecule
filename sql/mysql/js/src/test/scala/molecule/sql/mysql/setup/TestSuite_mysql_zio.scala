package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase_zio
import zio.ZLayer


trait TestSuite_mysql_zio extends CoreTestSuiteBase_zio with ConnectionJS_mysql {
  override val platform = "js"
  override val database = "Mysql"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.succeed(getConnection(schema))
  }
}
