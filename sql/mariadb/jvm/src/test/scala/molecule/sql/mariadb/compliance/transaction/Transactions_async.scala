package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_async


class Transactions_async extends Test {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_mariadb_async with DbProviders_mariadb {}
  )
}
