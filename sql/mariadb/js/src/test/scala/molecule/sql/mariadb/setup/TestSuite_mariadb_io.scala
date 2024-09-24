package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuite_io}


trait TestSuite_mariadb_io extends CoreTestSuite_io with ConnectionJS_mariadb {
  override val platform = "js"
  override val database = "MariaDB"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
