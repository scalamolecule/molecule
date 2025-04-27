package molecule.db.sql.sqlite.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_sqlite_async)
}
