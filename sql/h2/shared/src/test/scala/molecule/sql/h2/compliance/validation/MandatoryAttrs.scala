package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.sql.h2.setup.Api_h2_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_h2_async)
}
