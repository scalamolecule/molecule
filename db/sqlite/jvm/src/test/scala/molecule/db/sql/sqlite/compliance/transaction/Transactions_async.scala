package molecule.db.sql.sqlite.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql.sqlite.setup.Api_sqlite_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_sqlite_async)


}
