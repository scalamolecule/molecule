package molecule.sql.postgres.compliance.transaction

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction._
import molecule.sql.postgres.setup.DbProviders_postgres
import molecule.sql.postgres.spi.Spi_postgres_zio


class Transactions_zio extends Test {
  Transactions_zio(this,
    new Api_zio with Api_zio_transact with Spi_postgres_zio with DbProviders_postgres {}
  )
}
