package molecule.db.sql.h2.compliance.validation

import molecule.core.setup.MUnit_arrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.h2.setup.Api_h2_async

class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_h2_async)
}
