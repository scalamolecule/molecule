package molecule.db.sql.h2.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.h2.setup.Api_h2_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_h2_async)
}
