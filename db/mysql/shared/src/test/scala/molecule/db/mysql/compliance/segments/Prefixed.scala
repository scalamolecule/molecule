package molecule.db.mysql.compliance.segments

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.mysql.setup.Api_mysql_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_mysql_async)
}
