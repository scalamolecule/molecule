package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.Enumerations
import molecule.db.sql.postgres.setup.Api_postgres_async

class EnumerationsTest extends MUnit {
  Enumerations(this, Api_postgres_async)
}
