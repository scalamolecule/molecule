package molecule.db.h2.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.h2.setup.Api_h2_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_h2_async)
}
