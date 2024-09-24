package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase_zio
import zio.ZLayer


trait TestSuite_postgres_zio extends CoreTestSuiteBase_zio with ConnectionJS_postgres {
  override val platform = "js"
  override val database = "Postgres"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
    ZLayer.succeed(getConnection(schema))
  }
}
