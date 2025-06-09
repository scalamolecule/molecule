package molecule.db.sql.mysql.compliance.validation

import molecule.core.setup.MUnit_arrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.mysql.setup.Api_mysql_async

class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_mysql_async)
}
