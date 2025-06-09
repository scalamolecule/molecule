package molecule.db.sql.sqlite.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.sqlite.setup.Api_sqlite_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_sqlite_io)
}
