package molecule.db.sql.mysql.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class EnumerationsTest extends Test {
  Enumerations(this, Api_mysql_async)
}
