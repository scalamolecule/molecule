package molecule.db.sql.mariadb.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AsyncApiTest extends MUnit {
  AsyncApi(this, Api_mariadb_async)
}
