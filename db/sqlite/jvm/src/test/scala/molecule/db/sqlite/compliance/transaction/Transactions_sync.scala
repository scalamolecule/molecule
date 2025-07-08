package molecule.db.sqlite.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_sync
import molecule.db.sqlite.setup.Api_sqlite_sync


class Transactions_syncTest extends MUnit {
  Transactions_sync(this, Api_sqlite_sync)
}
