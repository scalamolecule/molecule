package molecule.db.sql.sqlite.compliance.validation

import molecule.db.compliance.setup.MUnitSuiteWithArrays
import molecule.db.compliance.test.validation.MandatoryRefs
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_sqlite_async)
}
