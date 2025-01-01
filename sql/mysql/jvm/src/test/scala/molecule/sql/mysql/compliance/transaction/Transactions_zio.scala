package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mysql.setup.Api_mysql_zio


class Transactions_zio extends Test {
  Transactions_zio(this, Api_mysql_zio)
}
