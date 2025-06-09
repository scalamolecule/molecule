package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.MUnit_arrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.postgres.setup.Api_postgres_async

class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_postgres_async)
}
