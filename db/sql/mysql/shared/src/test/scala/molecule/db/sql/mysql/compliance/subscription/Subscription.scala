package molecule.db.sql.mysql.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class SubscriptionTest extends Test {
  Subscription(this, Api_mysql_async)
}
