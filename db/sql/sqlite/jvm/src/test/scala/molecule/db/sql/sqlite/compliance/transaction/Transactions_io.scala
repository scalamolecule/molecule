package molecule.db.sql.sqlite.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.sqlite.setup.Api_sqlite_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_sqlite_io)
}
