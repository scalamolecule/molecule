package molecule.sql.sqlite.compliance.action

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_sync
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.spi.Spi_sqlite_sync


class Transactions_sync extends MUnitSuite {
  Transactions_sync(this,
    new Api_sync with Api_sync_transact with Spi_sqlite_sync with DbProviders_sqlite {}
  )
}
