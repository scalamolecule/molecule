package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation._
import molecule.sql.postgres.setup.Api_postgres_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_postgres_async)
}
