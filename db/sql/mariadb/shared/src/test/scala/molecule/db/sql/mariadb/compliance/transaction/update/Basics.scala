package molecule.db.sql.mariadb.compliance.transaction.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class BasicsTest extends Test {
  Basics(this, Api_mariadb_async)
}
