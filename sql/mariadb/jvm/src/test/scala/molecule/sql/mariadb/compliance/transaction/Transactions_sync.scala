package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mariadb.setup.Api_mariadb_sync


class Transactions_sync extends Test {
  Transactions_sync(this, Api_mariadb_sync)
}
