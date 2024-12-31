package molecule.sql.h2.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.spi.Spi_h2_async


class Transactions_async extends Test {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_h2_async with DbProviders_h2 {}
  )
}
