package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_async
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_async


class Transactions_async extends MUnitSuite {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_mariadb_async with DbProviders_mariadb {}
  )
}
