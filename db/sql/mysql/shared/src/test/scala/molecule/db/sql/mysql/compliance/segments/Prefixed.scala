package molecule.db.sql.mysql.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class PrefixedTest extends Test {
  Prefixed(this, Api_mysql_async)
}
