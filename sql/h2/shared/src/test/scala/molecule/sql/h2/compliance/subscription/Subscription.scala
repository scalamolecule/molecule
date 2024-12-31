package molecule.sql.h2.compliance.subscription

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.subscription._
import molecule.sql.h2.setup.Api_h2_async

class Subscription extends Test {
  Subscription(this, Api_h2_async)
}
