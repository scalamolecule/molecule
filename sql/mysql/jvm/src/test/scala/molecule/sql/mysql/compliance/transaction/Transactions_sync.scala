package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_sync
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_sync


class Transactions_sync extends MUnitSuite {
  Transactions_sync(this,
    new Api_sync with Api_sync_transact with Spi_mysql_sync with DbProviders_mysql {}
  )
}
