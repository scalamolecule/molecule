package molecule.db.sql.postgres.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_postgres_async)
}
