package molecule.db.sql.postgres.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql.postgres.setup.Api_postgres_async

class EnumerationsTest extends Test {
  Enumerations(this, Api_postgres_async)
}
