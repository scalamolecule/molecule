package molecule.sql.mysql.compliance.transaction

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.mysql.setup.DbProviders_mysql
import molecule.sql.mysql.spi.Spi_mysql_zio


class Transactions_zio extends Test {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_mysql_zio with DbProviders_mysql {}
  )
}
