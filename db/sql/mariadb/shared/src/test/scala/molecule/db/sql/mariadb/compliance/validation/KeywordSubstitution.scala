package molecule.db.sql.mariadb.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_mariadb_async)
}
