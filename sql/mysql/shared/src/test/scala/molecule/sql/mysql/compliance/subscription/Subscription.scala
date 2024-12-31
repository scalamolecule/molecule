package molecule.sql.mysql.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription._
import molecule.sql.mysql.setup.Api_mysql_async

class Subscription extends Test {
  Subscription(this, Api_mysql_async)
}
