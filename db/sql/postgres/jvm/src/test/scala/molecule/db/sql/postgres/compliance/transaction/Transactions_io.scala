package molecule.db.sql.postgres.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.postgres.setup.Api_postgres_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_postgres_io)
}
