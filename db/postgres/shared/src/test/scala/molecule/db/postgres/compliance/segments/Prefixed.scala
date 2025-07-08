package molecule.db.postgres.compliance.segments

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.postgres.setup.Api_postgres_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_postgres_async)
}
