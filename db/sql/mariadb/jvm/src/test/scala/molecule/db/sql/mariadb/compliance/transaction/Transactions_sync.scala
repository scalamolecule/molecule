package molecule.db.sql.mariadb.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_sync
import molecule.db.sql.mariadb.setup.Api_mariadb_sync


class Transactions_syncTest extends MUnit {
  Transactions_sync(this, Api_mariadb_sync)
}
