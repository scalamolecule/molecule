package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_sync
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_sync


class Transactions_sync extends MUnitSuite {
  Transactions_sync(this,
    new Api_sync with Api_sync_transact with Spi_mariadb_sync with DbProviders_mariadb {}
  )
}
