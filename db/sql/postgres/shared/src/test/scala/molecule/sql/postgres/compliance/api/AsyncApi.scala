package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.sql.postgres.setup.Api_postgres_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_postgres_async)
}
