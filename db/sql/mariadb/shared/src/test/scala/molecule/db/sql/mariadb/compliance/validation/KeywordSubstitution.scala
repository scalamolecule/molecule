package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_mariadb_async)
}
