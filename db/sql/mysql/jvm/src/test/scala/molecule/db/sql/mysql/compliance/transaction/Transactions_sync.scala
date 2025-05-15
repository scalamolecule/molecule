package molecule.db.sql.mysql.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_sync
import molecule.db.sql.mysql.setup.Api_mysql_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_mysql_sync)
}
