package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_sync


class Transactions_sync extends Test {
  Transactions_sync(this,
    new Api_sync with Api_sync_transact with Spi_mariadb_sync with DbProviders_mariadb {}
  )
}
