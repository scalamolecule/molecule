package molecule.db.sql.mariadb.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_mariadb_async)
}
