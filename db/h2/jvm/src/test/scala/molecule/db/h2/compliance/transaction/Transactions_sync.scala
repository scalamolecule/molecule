package molecule.db.h2.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_sync
import molecule.db.h2.setup.Api_h2_sync


class Transactions_syncTest extends MUnit {
  Transactions_sync(this, Api_h2_sync)
}
