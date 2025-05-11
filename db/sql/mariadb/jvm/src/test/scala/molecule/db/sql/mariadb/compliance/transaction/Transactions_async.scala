package molecule.db.sql.mariadb.compliance.transaction

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.Transactions_async
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class Transactions_asyncTest extends Test {
  Transactions_async(this, Api_mariadb_async)
}
