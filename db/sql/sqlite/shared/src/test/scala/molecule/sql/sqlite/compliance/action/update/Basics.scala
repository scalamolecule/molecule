package molecule.sql.sqlite.compliance.action.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class BasicsTest extends Test {
  Basics(this, Api_sqlite_async)
}
