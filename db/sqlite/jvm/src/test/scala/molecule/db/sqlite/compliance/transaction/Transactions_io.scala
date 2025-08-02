package molecule.db.sqlite.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.transaction.Transactions_io
import molecule.db.sqlite.setup.Api_sqlite_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_sqlite_io)
}
