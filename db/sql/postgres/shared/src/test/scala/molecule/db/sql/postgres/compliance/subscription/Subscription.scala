package molecule.db.sql.postgres.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class SubscriptionTest extends Test {
  Subscription(this, Api_postgres_async)
}
