package molecule.db.sql.h2.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.h2.setup.Api_h2_async

class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_h2_async)
}
