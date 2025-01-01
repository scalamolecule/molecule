package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.sqlite.setup.Api_sqlite_zio


class Transactions_zio extends Test {
  Transactions_zio(this, Api_sqlite_zio)
}
