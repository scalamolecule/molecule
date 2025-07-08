package molecule.db.sql.mysql.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.sql.mysql.setup.Api_mysql_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_mysql_io)
}
