package molecule.sql.h2.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.h2.setup.Api_h2_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_h2_async)
}
