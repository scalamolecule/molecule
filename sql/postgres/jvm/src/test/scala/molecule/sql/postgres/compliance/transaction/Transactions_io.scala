package molecule.sql.postgres.compliance.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.postgres.setup.DbProviders_postgres
import molecule.sql.postgres.spi.Spi_postgres_io


class Transactions_io extends Test {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_postgres_io with DbProviders_postgres {}
  )
}
