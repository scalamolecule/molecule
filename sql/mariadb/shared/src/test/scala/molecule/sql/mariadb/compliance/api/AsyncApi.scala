package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_async

class AsyncApi extends Test {
  AsyncApi(this, Api_mariadb_async)
}
