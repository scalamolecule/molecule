package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.sqlite.setup.Api_sqlite_sync


class Transactions_sync extends Test {
  Transactions_sync(this, Api_sqlite_sync)
}