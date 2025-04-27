package molecule.db.sql.sqlite.compliance.action.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class BasicsTest extends Test {
  Basics(this, Api_sqlite_async)
}
