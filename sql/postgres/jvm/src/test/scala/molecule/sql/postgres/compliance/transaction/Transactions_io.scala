package molecule.sql.postgres.compliance.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_io
import molecule.sql.postgres.setup.DbProviders_postgres
import molecule.sql.postgres.spi.Spi_postgres_io


class Transactions_io extends MUnitSuite {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_postgres_io with DbProviders_postgres {}
  )
}
