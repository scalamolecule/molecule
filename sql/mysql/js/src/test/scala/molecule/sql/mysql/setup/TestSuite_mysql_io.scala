package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestSuite


trait TestSuite_mysql_io extends CoreTestSuite with ConnectionJS_mysql {
  override val platform = "js"
  override val database = "Mysql"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    test(getConnection(schema))
  }
}
