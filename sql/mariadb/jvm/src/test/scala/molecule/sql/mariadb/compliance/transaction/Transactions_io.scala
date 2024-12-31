package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_io
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_io


class Transactions_io extends MUnitSuite {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_mariadb_io with DbProviders_mariadb {}
  )
}
