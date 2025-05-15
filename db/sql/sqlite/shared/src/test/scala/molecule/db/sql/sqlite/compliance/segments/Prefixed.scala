package molecule.db.sql.sqlite.compliance.segments

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class PrefixedTest extends Test {
  Prefixed(this, Api_sqlite_async)
}
