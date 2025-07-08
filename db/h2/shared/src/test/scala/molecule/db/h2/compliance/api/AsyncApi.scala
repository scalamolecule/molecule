package molecule.db.h2.compliance.api

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.h2.setup.Api_h2_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_h2_async)
}
