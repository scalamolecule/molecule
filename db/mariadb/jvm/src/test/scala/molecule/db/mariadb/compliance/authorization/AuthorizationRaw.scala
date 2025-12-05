package molecule.db.mariadb.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.mariadb.setup.Api_mariadb_async

class AuthorizationRaw extends MUnit {
  Authorization_raw_access(this, Api_mariadb_async)
}