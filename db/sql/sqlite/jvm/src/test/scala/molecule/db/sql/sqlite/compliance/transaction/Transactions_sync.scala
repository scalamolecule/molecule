package molecule.db.sql.sqlite.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_sync
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_sync


class Transactions_syncTest extends Test {
  Transactions_sync(this, Api_sqlite_sync)
}
