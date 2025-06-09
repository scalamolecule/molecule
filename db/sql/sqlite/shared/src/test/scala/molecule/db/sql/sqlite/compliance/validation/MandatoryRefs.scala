package molecule.db.sql.sqlite.compliance.validation

import molecule.core.setup.MUnit_arrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_sqlite_async)
}
