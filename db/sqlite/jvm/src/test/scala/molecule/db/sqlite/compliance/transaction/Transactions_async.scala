package molecule.db.sqlite.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.transaction.Transactions_async
import molecule.db.sqlite.setup.Api_sqlite_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_sqlite_async)


}
