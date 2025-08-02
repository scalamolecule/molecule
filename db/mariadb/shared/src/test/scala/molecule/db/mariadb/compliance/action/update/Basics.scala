package molecule.db.mariadb.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.Basics
import molecule.db.mariadb.setup.Api_mariadb_async

class BasicsTest extends MUnit {
  Basics(this, Api_mariadb_async)
}
