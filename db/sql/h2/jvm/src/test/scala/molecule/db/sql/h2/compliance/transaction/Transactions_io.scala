package molecule.db.sql.h2.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_h2_io)
}
