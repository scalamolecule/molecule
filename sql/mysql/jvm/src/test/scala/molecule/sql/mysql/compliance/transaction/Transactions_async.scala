package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_async


class Transactions_async extends Test {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_mysql_async with DbProviders_mysql {}
  )
}
