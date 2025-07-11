package molecule.db.postgresql.compliance.segments

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.postgresql.setup.Api_postgresql_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_postgresql_async)
}
