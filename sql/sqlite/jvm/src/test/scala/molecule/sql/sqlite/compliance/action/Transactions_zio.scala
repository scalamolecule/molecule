package molecule.sql.sqlite.compliance.action

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_zio
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.spi.Spi_sqlite_zio


class Transactions_zio extends MUnitSuite {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_sqlite_zio with DbProviders_sqlite {}
  )
}
