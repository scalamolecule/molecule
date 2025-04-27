package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.sql.mariadb.setup.Api_mariadb_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_mariadb_sync)
}
