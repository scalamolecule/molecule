package molecule.db.sql.postgres.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class StringValidationFnsTest extends Test {
  StringValidationFns(this, Api_postgres_async)
}
