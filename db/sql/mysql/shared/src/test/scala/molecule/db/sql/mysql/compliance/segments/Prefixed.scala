package molecule.db.sql.mysql.compliance.segments

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.mysql.setup.Api_mysql_async

class PrefixedTest extends Test {
  Prefixed(this, Api_mysql_async)
}
