package molecule.sql.postgres.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuite_io}


trait TestSuite_postgres_io extends CoreTestSuite_io with ConnectionJS_postgres {
  override val platform = "js"
  override val database = "Postgres"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
