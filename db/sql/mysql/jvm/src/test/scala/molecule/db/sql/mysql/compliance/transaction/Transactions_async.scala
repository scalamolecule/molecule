package molecule.db.sql.mysql.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql.mysql.setup.Api_mysql_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_mysql_async)
}
