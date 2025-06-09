package molecule.db.sql.mariadb.compliance.transaction.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class BasicsTest extends MUnit {
  Basics(this, Api_mariadb_async)
}
