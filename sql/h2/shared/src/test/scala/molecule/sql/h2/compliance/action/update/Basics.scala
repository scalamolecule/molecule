package molecule.sql.h2.compliance.action.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.sql.h2.setup.Api_h2_async

class BasicsTest extends Test {
  Basics(this, Api_h2_async)
}
