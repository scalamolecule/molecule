package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_zio
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_zio


class Transactions_zio extends MUnitSuite {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_mysql_zio with DbProviders_mysql {}
  )
}
