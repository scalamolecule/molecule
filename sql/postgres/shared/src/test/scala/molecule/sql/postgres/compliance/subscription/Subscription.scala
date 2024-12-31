package molecule.sql.postgres.compliance.subscription

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.postgres.setup.Api_postgres_async

class Subscription extends MUnitSuite {
  Subscription(this, Api_postgres_async)
}
