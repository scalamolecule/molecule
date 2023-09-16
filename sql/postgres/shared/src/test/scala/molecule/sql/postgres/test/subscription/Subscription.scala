package molecule.sql.postgres.test.subscription

import molecule.coreTests.test.subscription.Subscription
import molecule.sql.postgres.setup.TestAsync_postgres

object Subscription extends Subscription with TestAsync_postgres
