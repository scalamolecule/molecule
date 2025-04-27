package molecule.db.sql.mysql.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_mysql_async)
}
