package molecule.sql.h2.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor._
import molecule.sql.h2.setup.Api_h2_async

class SharedSemantics extends Test {
  SharedSemantics(this, Api_h2_async)
}
