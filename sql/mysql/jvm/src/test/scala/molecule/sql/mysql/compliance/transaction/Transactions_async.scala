package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_async
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_async


class Transactions_async extends MUnitSuite {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_mysql_async with DbProviders_mysql {}
  )
}
