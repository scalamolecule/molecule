package molecule.db.sql.h2.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.h2.setup.Api_h2_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_h2_async)
}
