package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.postgres.setup.Api_postgres_zio


class Transactions_zio extends Test {
  Transactions_zio(this, Api_postgres_zio)
}
