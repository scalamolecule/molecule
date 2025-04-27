package molecule.db.sql.sqlite.compliance.pagination.cursor

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_sqlite_async)
}
