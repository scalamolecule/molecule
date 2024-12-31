package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.h2.setup.Api_h2_async

class RequiredAttrs extends MUnitSuite {
  RequiredAttrs(this, Api_h2_async)
}
