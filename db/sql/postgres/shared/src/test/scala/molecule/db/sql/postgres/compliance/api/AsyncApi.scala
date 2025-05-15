package molecule.db.sql.postgres.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.postgres.setup.Api_postgres_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_postgres_async)
}
