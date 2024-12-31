package molecule.sql.sqlite.compliance.action

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.sqlite.setup.DbProviders_sqlite
import molecule.sql.sqlite.spi.Spi_sqlite_zio


class Transactions_zio extends Test {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_sqlite_zio with DbProviders_sqlite {}
  )
}
