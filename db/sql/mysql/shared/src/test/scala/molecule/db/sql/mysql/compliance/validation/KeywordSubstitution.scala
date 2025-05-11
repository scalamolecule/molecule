package molecule.db.sql.mysql.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_mysql_async)
}
