package molecule.db.sql.postgres.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql.postgres.setup.Api_postgres_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_postgres_async)
}
