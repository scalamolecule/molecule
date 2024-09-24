package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase_zio
import zio.ZLayer


trait TestSuite_mariadb_zio extends CoreTestSuiteBase_zio with ConnectionJS_mariadb {
  override val platform = "js"
  override val database = "MariaDB"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.succeed(getConnection(schema))
  }
}
