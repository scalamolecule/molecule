package molecule.db.sql.h2.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_h2_async)
}
