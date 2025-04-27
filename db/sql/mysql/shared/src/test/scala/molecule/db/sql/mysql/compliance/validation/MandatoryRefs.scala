package molecule.db.sql.mysql.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mysql_async)
}
