package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.sql.h2.setup.Api_h2_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_h2_async)
}
