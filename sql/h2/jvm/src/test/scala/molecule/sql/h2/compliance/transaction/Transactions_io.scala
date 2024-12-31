package molecule.sql.h2.compliance.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.spi.Spi_h2_io


class Transactions_io extends Test {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_h2_io with DbProviders_h2 {}
  )
}
