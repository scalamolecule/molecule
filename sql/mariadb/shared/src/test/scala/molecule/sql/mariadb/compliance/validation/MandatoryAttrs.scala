package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.sql.mariadb.setup.Api_mariadb_async

class MandatoryAttrsTest extends Test {
  MandatoryAttrs(this, Api_mariadb_async)
}
