package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.h2.setup.Api_h2_async

class MandatoryAttrs extends MUnitSuite {
  MandatoryAttrs(this, Api_h2_async)
}
