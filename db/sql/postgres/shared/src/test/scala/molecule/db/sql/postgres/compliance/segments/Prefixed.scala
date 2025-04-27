package molecule.db.sql.postgres.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class PrefixedTest extends Test {
  Prefixed(this, Api_postgres_async)
}
