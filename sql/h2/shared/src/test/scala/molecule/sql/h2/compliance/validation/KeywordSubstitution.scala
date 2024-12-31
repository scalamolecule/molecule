package molecule.sql.h2.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.h2.setup.Api_h2_async

class KeywordSubstitution extends MUnitSuite {
  KeywordSubstitution(this, Api_h2_async)
}
