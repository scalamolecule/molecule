package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation._
import molecule.sql.mariadb.setup.Api_mariadb_async

class MandatoryRefs extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mariadb_async)
}
