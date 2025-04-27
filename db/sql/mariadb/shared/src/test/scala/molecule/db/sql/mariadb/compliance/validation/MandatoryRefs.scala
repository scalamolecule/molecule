package molecule.db.sql.mariadb.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_mariadb_async)
}
