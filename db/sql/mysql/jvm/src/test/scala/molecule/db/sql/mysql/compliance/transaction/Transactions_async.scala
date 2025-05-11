package molecule.db.sql.mysql.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_mysql_async)
}
