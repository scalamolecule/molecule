package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.sql.mariadb.setup.Api_mariadb_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_mariadb_async)
}
