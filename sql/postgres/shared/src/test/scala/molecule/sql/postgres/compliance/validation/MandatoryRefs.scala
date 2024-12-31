package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.postgres.setup.Api_postgres_async

class MandatoryRefs extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_postgres_async)
}
