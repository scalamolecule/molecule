package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.sql.sqlite.setup.Api_sqlite_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_sqlite_async)
}
