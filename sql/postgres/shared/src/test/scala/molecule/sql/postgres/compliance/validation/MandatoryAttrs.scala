package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.sql.postgres.setup.Api_postgres_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_postgres_async)
}
