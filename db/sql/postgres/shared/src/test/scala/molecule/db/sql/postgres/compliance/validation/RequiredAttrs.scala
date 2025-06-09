package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.postgres.setup.Api_postgres_async

class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_postgres_async)
}
