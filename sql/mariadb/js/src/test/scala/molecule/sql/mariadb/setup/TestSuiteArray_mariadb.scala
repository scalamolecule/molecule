package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase


trait TestSuiteArray_mariadb extends CoreTestSuiteBase with ConnectionJS_mariadb {
  override val platform              = "js"
  override val database              = "MariaDB"
  override val isJsPlatform: Boolean = true

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
