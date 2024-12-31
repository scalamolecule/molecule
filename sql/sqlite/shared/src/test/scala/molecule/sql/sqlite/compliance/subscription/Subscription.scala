package molecule.sql.sqlite.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription._
import molecule.sql.sqlite.setup.Api_sqlite_async

class Subscription extends Test {
  Subscription(this, Api_sqlite_async)
}
