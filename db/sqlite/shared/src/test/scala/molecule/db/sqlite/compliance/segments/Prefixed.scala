package molecule.db.sqlite.compliance.segments

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sqlite.setup.Api_sqlite_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_sqlite_async)
}
