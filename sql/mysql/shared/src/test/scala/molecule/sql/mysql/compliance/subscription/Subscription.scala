package molecule.sql.mysql.compliance.subscription

import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.mysql.setup.TestAsync_mysql

object Subscription extends Subscription with TestAsync_mysql
