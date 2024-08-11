package molecule.sql.mariadb.compliance.subscription

import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_Subscription extends Subscription with TestAsync_mariadb
