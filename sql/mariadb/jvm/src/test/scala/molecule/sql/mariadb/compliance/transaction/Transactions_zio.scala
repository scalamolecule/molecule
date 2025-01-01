package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mariadb.setup.Api_mariadb_zio


class Transactions_zio extends Test {
  Transactions_zio(this, Api_mariadb_zio)
}
