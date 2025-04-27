package molecule.db.sql.sqlite.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_sqlite_async)
}
