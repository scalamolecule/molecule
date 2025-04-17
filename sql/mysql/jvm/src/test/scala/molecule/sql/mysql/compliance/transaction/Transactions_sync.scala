package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action._
import molecule.sql.mysql.setup.Api_mysql_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_mysql_sync)
}
