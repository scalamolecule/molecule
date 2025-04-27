package molecule.sql.sqlite.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_sqlite_async)
}
