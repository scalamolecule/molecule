package molecule.db.sql.mysql.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_mysql_sync)
}
