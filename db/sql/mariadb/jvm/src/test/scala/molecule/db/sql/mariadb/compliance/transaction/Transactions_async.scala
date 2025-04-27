package molecule.db.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_mariadb_async)
}
