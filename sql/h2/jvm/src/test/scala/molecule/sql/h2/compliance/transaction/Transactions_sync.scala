package molecule.sql.h2.compliance.transaction

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.spi.Spi_h2_sync


class Transactions_sync extends Test {
  Transactions_sync(this,
    new Api_sync with Api_sync_transact with Spi_h2_sync with DbProviders_h2 {}
  )
}
