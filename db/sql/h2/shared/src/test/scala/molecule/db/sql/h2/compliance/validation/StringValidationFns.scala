package molecule.db.sql.h2.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_h2_async)
}
