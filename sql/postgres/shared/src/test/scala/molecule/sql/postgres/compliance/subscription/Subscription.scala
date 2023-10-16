package molecule.sql.postgres.compliance.subscription

import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.postgres.setup.TestAsync_postgres

object Subscription extends Subscription with TestAsync_postgres
