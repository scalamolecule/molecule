package molecule.sql.mysql.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments._
import molecule.sql.mysql.setup.Api_mysql_async

class PrefixedTest extends Test {
  Prefixed(this, Api_mysql_async)
}
