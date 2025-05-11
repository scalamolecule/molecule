package molecule.db.sql.sqlite.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_sqlite_async)
}
