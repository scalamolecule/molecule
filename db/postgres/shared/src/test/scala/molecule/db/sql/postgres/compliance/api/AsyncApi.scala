package molecule.db.sql.postgres.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.postgres.setup.Api_postgres_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_postgres_async)
}
