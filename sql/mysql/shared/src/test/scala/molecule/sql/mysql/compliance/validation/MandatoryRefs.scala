package molecule.sql.mysql.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.mysql.setup.Api_mysql_async

class MandatoryRefs extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mysql_async)
}
