package molecule.db.postgresql.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.postgresql.setup.Api_postgresql_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_postgresql_async)
}
