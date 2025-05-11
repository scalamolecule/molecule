package molecule.db.sql.mariadb.compliance.validation

import molecule.db.compliance.setup.MUnitSuiteWithArrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mariadb_async)
}
