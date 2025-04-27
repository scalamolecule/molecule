package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.sql.sqlite.setup.Api_sqlite_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_sqlite_sync)
}
