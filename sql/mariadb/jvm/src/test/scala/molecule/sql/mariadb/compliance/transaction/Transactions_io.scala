package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mariadb.setup.Api_mariadb_io


class Transactions_io extends Test {
  Transactions_io(this, Api_mariadb_io)
}
