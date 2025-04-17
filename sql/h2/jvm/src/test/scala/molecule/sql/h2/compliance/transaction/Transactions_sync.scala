package molecule.sql.h2.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.h2.setup.Api_h2_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_h2_sync)
}
