package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase


trait TestSuite_postgres_array extends CoreTestSuiteBase with ConnectionJS_postgres {
  override val platform     = "js"
  override val database     = "Postgres"
  override val isJsPlatform = true

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
