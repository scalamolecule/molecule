package molecule.sql.mariadb.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor._
import molecule.sql.mariadb.setup.Api_mariadb_async

class SharedSemantics extends Test {
  SharedSemantics(this, Api_mariadb_async)
}
