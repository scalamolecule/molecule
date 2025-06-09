package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.StringValidationFns
import molecule.db.sql.postgres.setup.Api_postgres_async

class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_postgres_async)
}
