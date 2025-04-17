package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation._
import molecule.sql.mariadb.setup.Api_mariadb_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_mariadb_async)
}
