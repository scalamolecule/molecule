package molecule.db.sql.mariadb.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_mariadb_async)
}
