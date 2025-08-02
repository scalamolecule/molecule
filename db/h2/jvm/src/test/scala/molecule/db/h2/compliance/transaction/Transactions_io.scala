package molecule.db.h2.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.transaction.Transactions_io
import molecule.db.h2.setup.Api_h2_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_h2_io)
}
