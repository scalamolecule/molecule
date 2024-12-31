package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.h2.setup.Api_h2_async

class AsyncApi extends Test {
  AsyncApi(this, Api_h2_async)
}
