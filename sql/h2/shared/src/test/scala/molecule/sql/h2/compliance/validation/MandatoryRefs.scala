package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.h2.setup.Api_h2_async

class MandatoryRefs extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_h2_async)
}
