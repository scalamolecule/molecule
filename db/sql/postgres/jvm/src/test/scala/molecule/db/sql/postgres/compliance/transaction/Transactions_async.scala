package molecule.db.sql.postgres.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql.postgres.setup.Api_postgres_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_postgres_async)
}
