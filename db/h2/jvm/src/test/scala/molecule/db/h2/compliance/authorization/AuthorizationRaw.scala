package molecule.db.h2.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.h2.setup.Api_h2_async

class AuthorizationRaw extends MUnit {
  Authorization_raw_access(this, Api_h2_async)
}