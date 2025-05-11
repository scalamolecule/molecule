package molecule.db.sql.mariadb.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.AsyncApi
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_mariadb_async)
}
