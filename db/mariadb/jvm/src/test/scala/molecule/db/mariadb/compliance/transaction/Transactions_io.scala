package molecule.db.mariadb.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_io
import molecule.db.mariadb.setup.Api_mariadb_io


class Transactions_ioTest extends MUnit {
  Transactions_io(this, Api_mariadb_io)
}
