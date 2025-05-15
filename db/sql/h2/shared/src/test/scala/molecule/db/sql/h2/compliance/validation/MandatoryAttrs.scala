package molecule.db.sql.h2.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.h2.setup.Api_h2_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_h2_async)
}
