package molecule.db.sql.h2.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql.h2.setup.Api_h2_async

class EnumerationsTest extends MUnit {
  Enumerations(this, Api_h2_async)
}
