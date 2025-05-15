package molecule.db.sql.mysql.compliance.validation

import molecule.db.compliance.setup.MUnitSuiteWithArrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql.mysql.setup.Api_mysql_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mysql_async)
}
