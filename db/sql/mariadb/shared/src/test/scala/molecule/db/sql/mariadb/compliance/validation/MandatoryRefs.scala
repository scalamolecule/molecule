package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.MUnit_arrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_mariadb_async)
}
