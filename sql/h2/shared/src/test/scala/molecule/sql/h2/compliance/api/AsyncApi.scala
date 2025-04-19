package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.sql.h2.setup.Api_h2_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_h2_async)
}
