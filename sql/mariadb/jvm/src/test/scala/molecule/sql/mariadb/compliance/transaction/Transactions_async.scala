package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mariadb.setup.Api_mariadb_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_mariadb_async)
}
