package molecule.sql.mariadb.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.sql.mariadb.marshalling.Connection_mariadb


trait TestSuiteArray_mariadb extends CoreTestSuiteBase with BaseHelpers {

  override val platform = "jvm"
  override val database = "MariaDB"
  override val isJsPlatform: Boolean = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(Connection_mariadb.getConnection(schema))
  }
}
