package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.postgres.setup.Api_postgres_async

class KeywordSubstitution extends MUnitSuite {
  KeywordSubstitution(this, Api_postgres_async)
}
