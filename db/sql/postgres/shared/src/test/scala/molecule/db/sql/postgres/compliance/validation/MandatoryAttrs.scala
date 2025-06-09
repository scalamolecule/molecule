package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.MandatoryAttrs
import molecule.db.sql.postgres.setup.Api_postgres_async

class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_postgres_async)
}
