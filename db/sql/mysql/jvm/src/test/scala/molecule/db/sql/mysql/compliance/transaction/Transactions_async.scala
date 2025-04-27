package molecule.db.sql.mysql.compliance.transaction

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_mysql_async)
}
