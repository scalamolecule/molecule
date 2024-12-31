package molecule.sql.mysql.compliance.pagination.cursor

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.mysql.setup.Api_mysql_async

class SharedSemantics extends MUnitSuite {
  SharedSemantics(this, Api_mysql_async)
}
