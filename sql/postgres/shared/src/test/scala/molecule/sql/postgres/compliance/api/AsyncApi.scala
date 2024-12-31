package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Api_postgres_async

class AsyncApi extends MUnitSuite {
  AsyncApi(this, Api_postgres_async)
}
