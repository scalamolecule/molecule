package molecule.sql.h2.compliance.transaction

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.spi.Spi_h2_zio


class Transactions_zio extends Test {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_h2_zio with DbProviders_h2 {}
  )
}
