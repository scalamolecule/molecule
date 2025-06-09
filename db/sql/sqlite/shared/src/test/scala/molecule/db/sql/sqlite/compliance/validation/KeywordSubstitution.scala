package molecule.db.sql.sqlite.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_sqlite_async)
}
