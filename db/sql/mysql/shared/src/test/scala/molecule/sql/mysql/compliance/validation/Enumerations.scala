package molecule.sql.mysql.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.sql.mysql.setup.Api_mysql_async

class EnumerationsTest extends Test {
  Enumerations(this, Api_mysql_async)
}
