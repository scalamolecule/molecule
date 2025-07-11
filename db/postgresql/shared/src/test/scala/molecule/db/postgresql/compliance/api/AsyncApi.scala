package molecule.db.postgresql.compliance.api

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.postgresql.setup.Api_postgresql_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_postgresql_async)
}
