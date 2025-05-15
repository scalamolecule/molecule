package molecule.db.sql.postgres.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.postgres.setup.Api_postgres_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_postgres_io)
}
