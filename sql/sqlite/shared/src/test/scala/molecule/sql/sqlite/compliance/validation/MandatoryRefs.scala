package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.sqlite.setup.Api_sqlite_async

class MandatoryRefs extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_sqlite_async)
}
