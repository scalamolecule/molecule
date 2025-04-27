package molecule.sql.postgres.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.sql.postgres.setup.Api_postgres_async

class PrefixedTest extends Test {
  Prefixed(this, Api_postgres_async)
}
