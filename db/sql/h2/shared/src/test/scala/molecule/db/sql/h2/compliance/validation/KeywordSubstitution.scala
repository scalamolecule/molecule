package molecule.db.sql.h2.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.h2.setup.Api_h2_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_h2_async)
}
