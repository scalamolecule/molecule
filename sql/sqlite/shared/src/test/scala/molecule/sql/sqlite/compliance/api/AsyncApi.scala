package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.AsyncApi
import molecule.sql.sqlite.setup.Api_sqlite_async

class AsyncApi extends MUnitSuite {
  AsyncApi(this, Api_sqlite_async)
}
