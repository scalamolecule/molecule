package molecule.db.sql.h2.compliance.validation

import molecule.db.compliance.setup.MUnitSuiteWithArrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.h2.setup.Api_h2_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_h2_async)
}
