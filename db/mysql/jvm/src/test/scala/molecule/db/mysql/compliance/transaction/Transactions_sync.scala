package molecule.db.mysql.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.transaction.Transactions_sync
import molecule.db.mysql.setup.Api_mysql_sync


class Transactions_syncTest extends MUnit {
  Transactions_sync(this, Api_mysql_sync)
}
