package molecule.db.sql.mysql.compliance.segments

import molecule.core.setup.MUnit
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.mysql.setup.Api_mysql_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_mysql_async)
}
