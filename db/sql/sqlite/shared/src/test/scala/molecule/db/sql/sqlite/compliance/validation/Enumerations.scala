package molecule.db.sql.sqlite.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class EnumerationsTest extends Test {
  Enumerations(this, Api_sqlite_async)
}
