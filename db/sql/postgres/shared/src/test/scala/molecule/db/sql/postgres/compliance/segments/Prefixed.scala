package molecule.db.sql.postgres.compliance.segments

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.postgres.setup.Api_postgres_async

class PrefixedTest extends Test {
  Prefixed(this, Api_postgres_async)
}
