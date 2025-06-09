package molecule.db.sql.sqlite.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class EnumerationsTest extends MUnit {
  Enumerations(this, Api_sqlite_async)
}
