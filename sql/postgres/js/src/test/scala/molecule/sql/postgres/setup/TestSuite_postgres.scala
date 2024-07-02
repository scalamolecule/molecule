package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite


trait TestSuite_postgres extends CoreTestSuite with ConnectionJS_postgres {
  override val platform = "js"
  override val database = "Postgres"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
