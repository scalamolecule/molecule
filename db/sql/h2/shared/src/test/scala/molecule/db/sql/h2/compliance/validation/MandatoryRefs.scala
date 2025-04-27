package molecule.db.sql.h2.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_h2_async)
}
