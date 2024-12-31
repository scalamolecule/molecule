package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_io


class Transactions_io extends Test {
  Transactions_io(this,
    new Api_io with Api_io_transact with Spi_mysql_io with DbProviders_mysql {}
  )
}
