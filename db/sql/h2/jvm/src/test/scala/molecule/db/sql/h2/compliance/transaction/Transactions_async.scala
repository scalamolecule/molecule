package molecule.db.sql.h2.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_h2_async)
}
