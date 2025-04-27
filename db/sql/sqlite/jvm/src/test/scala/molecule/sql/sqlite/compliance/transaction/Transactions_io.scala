package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.sql.sqlite.setup.Api_sqlite_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_sqlite_io)
}
