package molecule.sql.h2.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.h2.setup.Api_h2_zio


class Transactions_zio extends Test {
  Transactions_zio(this, Api_h2_zio)
}
