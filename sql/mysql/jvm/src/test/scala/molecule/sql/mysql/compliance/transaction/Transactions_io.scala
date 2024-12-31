package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_io
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_io


class Transactions_io extends MUnitSuite {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_mysql_io with DbProviders_mysql {}
  )
}
