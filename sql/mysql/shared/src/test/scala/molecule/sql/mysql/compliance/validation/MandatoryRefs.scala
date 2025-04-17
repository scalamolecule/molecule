package molecule.sql.mysql.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation._
import molecule.sql.mysql.setup.Api_mysql_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mysql_async)
}
