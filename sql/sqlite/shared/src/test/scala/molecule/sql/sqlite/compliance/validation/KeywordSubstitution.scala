package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_sqlite_async)
}
