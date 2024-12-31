package molecule.sql.sqlite.compliance.action

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.spi.Spi_sqlite_async


class Transactions_async extends Test {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_sqlite_async with DbProviders_sqlite {}
  )
}
