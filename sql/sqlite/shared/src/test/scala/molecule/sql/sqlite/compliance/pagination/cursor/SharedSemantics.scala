package molecule.sql.sqlite.compliance.pagination.cursor

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.SharedSemantics
import molecule.sql.sqlite.setup.Api_sqlite_async

class SharedSemantics extends MUnitSuite {
  SharedSemantics(this, Api_sqlite_async)
}
