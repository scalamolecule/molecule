package molecule.sql.postgres.compliance.transaction.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update._
import molecule.sql.postgres.setup.Api_postgres_async

class Basics extends Test {
  Basics(this, Api_postgres_async)
}
