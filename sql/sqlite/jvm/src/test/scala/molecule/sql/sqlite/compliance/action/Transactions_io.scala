package molecule.sql.sqlite.compliance.action

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.spi.Spi_sqlite_io


class Transactions_io extends Test {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_sqlite_io with DbProviders_sqlite {}
  )
}
