package molecule.db.postgresql.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.postgresql.setup.Api_postgresql_async

class AuthorizationRaw extends MUnit {
  Authorization_raw_access(this, Api_postgresql_async)
}