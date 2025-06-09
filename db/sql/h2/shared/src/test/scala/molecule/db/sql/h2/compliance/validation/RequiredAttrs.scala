package molecule.db.sql.h2.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.h2.setup.Api_h2_async

class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_h2_async)
}
