package molecule.sql.postgres.compliance.subscription

import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.postgres.setup.Test_postgres_async

object Test_Subscription extends Subscription with Test_postgres_async
