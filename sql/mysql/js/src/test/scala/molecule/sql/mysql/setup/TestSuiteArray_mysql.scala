package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuiteBase


trait TestSuiteArray_mysql extends CoreTestSuiteBase with ConnectionJS_mysql {
  override val platform              = "js"
  override val database              = "Mysql"
  override val isJsPlatform: Boolean = true

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
