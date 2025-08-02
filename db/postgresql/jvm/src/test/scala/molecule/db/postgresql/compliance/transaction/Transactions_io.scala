package molecule.db.postgresql.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.transaction.Transactions_io
import molecule.db.postgresql.setup.Api_postgresql_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_postgresql_io)
}
