package molecule.sql.h2.compliance.pagination.cursor

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.h2.setup.Api_h2_async

class SharedSemantics extends MUnitSuite {
  SharedSemantics(this, Api_h2_async)
}
