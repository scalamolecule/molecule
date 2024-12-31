package molecule.sql.postgres.compliance.transaction

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.postgres.setup.DbProviders_postgres
import molecule.sql.postgres.spi.Spi_postgres_async


class Transactions_async extends Test {
  Transactions_async(this,
    new Api_async with Api_async_transact with Spi_postgres_async with DbProviders_postgres {}
  )
}
