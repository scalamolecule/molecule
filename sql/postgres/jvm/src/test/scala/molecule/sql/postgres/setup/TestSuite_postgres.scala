package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.postgres.marshalling.Connection_postgres


trait TestSuite_postgres extends CoreTestSuite with BaseHelpers {
  override val platform     = "jvm"
  override val database     = "Postgres"
  override val isJsPlatform = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    // For speed, re-use the same connection for all tests
    // by dropping the previous database before each test.
    test(Connection_postgres.getConnection(schema))
  }
}
