package molecule.db.sql.postgres.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.RequiredAttrs
import molecule.db.sql.postgres.setup.Api_postgres_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_postgres_async)
}
