package molecule.sql.sqlite.compliance.action

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_async
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.spi.Spi_sqlite_async


class Transactions_async extends MUnitSuite {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_sqlite_async with DbProviders_sqlite {}
  )
}
