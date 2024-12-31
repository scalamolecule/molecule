package molecule.sql.mysql.compliance.subscription

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.subscription.Subscription
import molecule.sql.mysql.setup.Api_mysql_async

class Subscription extends MUnitSuite {
  Subscription(this, Api_mysql_async)
}
