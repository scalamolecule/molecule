package molecule.db.mariadb.compliance.api

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.mariadb.setup.Api_mariadb_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_mariadb_async)
}
