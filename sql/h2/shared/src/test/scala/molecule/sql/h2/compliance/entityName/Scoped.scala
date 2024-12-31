package molecule.sql.h2.compliance.entityName

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.entityName.Scoped
import molecule.sql.h2.setup.Api_h2_async

class Scoped extends MUnitSuite {
  Scoped(this, Api_h2_async)
}
