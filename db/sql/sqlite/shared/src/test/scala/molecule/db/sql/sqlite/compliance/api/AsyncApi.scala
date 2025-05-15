package molecule.db.sql.sqlite.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_sqlite_async)
}
