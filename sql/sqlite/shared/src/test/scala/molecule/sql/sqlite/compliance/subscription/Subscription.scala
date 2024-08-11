package molecule.sql.sqlite.compliance.subscription

import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_Subscription extends Subscription with TestAsync_sqlite
