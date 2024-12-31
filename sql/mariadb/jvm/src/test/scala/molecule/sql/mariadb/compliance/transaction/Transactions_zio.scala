package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_zio


class Transactions_zio extends Test {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_mariadb_zio with DbProviders_mariadb {}
  )
}
