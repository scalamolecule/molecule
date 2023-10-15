package molecule.sql.postgres.compliance.subscription

import molecule.coreTests.compliance.subscription.Subscription
import molecule.sql.postgres.setup.TestAsync_postgres

object Subscription extends Subscription with TestAsync_postgres
