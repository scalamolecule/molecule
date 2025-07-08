package molecule.db.sql.sqlite.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_sqlite_async)
}
