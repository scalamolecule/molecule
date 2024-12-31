package molecule.sql.mariadb.compliance.transaction

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_zio
import molecule.sql.mariadb.setup.DbProviders_mariadb
import molecule.sql.mariadb.spi.Spi_mariadb_zio


class Transactions_zio extends MUnitSuite {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_mariadb_zio with DbProviders_mariadb {}
  )
}
