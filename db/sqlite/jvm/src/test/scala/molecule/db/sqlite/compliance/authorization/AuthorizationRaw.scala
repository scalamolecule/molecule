package molecule.db.sqlite.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.sqlite.setup.Api_sqlite_async

class AuthorizationRaw extends MUnit {
  Authorization_raw_access(this, Api_sqlite_async)
}