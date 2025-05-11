package molecule.db.sql.mysql.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_io


class Transactions_ioTest extends Test {
  Transactions_io(this, Api_mysql_io)
}
