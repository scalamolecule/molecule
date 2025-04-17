package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.sql.sqlite.setup.Api_sqlite_async

class EnumerationsTest extends Test {
  Enumerations(this, Api_sqlite_async)
}
