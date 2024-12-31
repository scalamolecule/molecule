package molecule.sql.mariadb.compliance.pagination.cursor

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.mariadb.setup.Api_mariadb_async

class SharedSemantics extends MUnitSuite {
  SharedSemantics(this, Api_mariadb_async)
}
