package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mysql.setup.Api_mysql_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_mysql_io)
}
