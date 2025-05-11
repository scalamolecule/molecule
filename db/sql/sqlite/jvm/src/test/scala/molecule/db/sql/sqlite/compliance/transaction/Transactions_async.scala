package molecule.db.sql.sqlite.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_sqlite_async)


}
