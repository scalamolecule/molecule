package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mysql.setup.Api_mysql_async


class Transactions_async extends Test {
  Transactions_async(this, Api_mysql_async)
}
