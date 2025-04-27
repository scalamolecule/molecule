package molecule.db.sql.mariadb.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_mariadb_async)
}
