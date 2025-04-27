package molecule.db.sql.h2.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_h2_io)
}
