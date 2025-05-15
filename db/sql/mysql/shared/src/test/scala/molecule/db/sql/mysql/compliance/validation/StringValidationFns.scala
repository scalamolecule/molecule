package molecule.db.sql.mysql.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.mysql.setup.Api_mysql_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_mysql_async)
}
