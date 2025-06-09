package molecule.db.sql.mariadb.compliance.transaction

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class Transactions_asyncTest extends MUnit {
  Transactions_async(this, Api_mariadb_async)
}
