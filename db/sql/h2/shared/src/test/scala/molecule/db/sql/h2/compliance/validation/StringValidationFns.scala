package molecule.db.sql.h2.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.h2.setup.Api_h2_async

class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_h2_async)
}
