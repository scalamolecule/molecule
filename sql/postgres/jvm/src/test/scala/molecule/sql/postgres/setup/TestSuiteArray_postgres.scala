package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.sql.postgres.marshalling.Connection_postgres


trait TestSuiteArray_postgres extends CoreTestSuiteBase with BaseHelpers {

  override val platform     = "jvm"
  override val database     = "Postgres"
  override val isJsPlatform = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(Connection_postgres.getConnection(schema))
  }
}
