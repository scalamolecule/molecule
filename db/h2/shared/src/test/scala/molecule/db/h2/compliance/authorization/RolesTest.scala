package molecule.db.h2.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.Roles
import molecule.db.h2.setup.Api_h2_async

class RolesTest extends MUnit {
  Roles(this, Api_h2_async)
}
