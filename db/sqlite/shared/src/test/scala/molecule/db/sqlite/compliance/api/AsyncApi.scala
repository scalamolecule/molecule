package molecule.db.sqlite.compliance.api

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sqlite.setup.Api_sqlite_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_sqlite_async)
}
