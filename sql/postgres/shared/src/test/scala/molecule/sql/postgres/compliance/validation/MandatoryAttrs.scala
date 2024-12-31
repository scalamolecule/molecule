package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.postgres.setup.Api_postgres_async

class MandatoryAttrs extends MUnitSuite {
  MandatoryAttrs(this, Api_postgres_async)
}
