package molecule.sql.postgres.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_async
import molecule.sql.postgres.setup.DbProviders_postgres
import molecule.sql.postgres.spi.Spi_postgres_async


class Transactions_async extends MUnitSuite {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_postgres_async with DbProviders_postgres {}
  )
}
