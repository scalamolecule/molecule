package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.sql.mysql.marshalling.Connection_mysql


trait TestSuite_mysql_array extends CoreTestSuiteBase with BaseHelpers {

  override val platform              = "jvm"
  override val database              = "Mysql"
  override val isJsPlatform: Boolean = false

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(Connection_mysql.getConnection(schema))
  }
}
