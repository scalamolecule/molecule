package molecule.sql.mariadb.compliance.subscription

import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_Subscription extends Subscription with Test_mariadb_async
