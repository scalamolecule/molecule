package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite


trait TestSuite_mariadb extends CoreTestSuite with ConnectionJS_mariadb {
  override val platform = "js"
  override val database = "MariaDB"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
