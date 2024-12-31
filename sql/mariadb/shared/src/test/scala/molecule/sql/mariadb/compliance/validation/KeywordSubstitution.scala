package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.mariadb.setup.Api_mariadb_async

class KeywordSubstitution extends MUnitSuite {
  KeywordSubstitution(this, Api_mariadb_async)
}
