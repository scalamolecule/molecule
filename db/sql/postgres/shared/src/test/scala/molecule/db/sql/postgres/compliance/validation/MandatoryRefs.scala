package molecule.db.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuiteWithArrays
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class MandatoryRefsTest extends MUnitSuiteWithArrays {
  MandatoryRefs(this, Api_postgres_async)
}
