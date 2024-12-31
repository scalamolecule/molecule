package molecule.sql.mysql.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor._
import molecule.sql.mysql.setup.Api_mysql_async

class SharedSemantics extends Test {
  SharedSemantics(this, Api_mysql_async)
}
