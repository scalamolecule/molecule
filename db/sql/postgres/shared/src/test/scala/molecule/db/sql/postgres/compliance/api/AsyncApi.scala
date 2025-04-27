package molecule.db.sql.postgres.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_postgres_async)
}
