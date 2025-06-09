package molecule.db.sql.h2.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.h2.setup.Api_h2_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_h2_io)
}
