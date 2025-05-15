package molecule.db.sql.mariadb.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.mariadb.setup.Api_mariadb_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_mariadb_io)
}
