package molecule.db.sql.sqlite.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_sqlite_async)
}
