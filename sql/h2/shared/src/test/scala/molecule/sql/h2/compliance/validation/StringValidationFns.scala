package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.h2.setup.Api_h2_async

class StringValidationFns extends MUnitSuite {
  StringValidationFns(this, Api_h2_async)
}
