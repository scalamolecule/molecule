package molecule.db.sql.sqlite.compliance.segments

import molecule.core.setup.MUnit
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_sqlite_async)
}
