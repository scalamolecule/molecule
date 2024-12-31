package molecule.sql.postgres.compliance.entityName

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.entityName._
import molecule.sql.postgres.setup.Api_postgres_async

class Scoped extends Test {
  Scoped(this, Api_postgres_async)
}
