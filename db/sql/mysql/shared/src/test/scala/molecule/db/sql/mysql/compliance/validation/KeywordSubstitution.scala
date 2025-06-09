package molecule.db.sql.mysql.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.mysql.setup.Api_mysql_async

class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_mysql_async)
}
