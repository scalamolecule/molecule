package molecule.db.sql.mariadb.compliance.transaction.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class BasicsTest extends Test {
  Basics(this, Api_mariadb_async)
}
