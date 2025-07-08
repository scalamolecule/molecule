package molecule.db.postgres.compliance.api

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.postgres.setup.Api_postgres_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_postgres_async)
}
