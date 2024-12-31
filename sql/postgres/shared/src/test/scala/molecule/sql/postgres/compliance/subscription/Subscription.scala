package molecule.sql.postgres.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription._
import molecule.sql.postgres.setup.Api_postgres_async

class Subscription extends Test {
  Subscription(this, Api_postgres_async)
}
