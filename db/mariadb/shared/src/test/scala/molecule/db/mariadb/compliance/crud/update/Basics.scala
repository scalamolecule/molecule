package molecule.db.mariadb.compliance.crud.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.Basics
import molecule.db.mariadb.setup.Api_mariadb_async

class BasicsTest extends MUnit {
  Basics(this, Api_mariadb_async)
}
