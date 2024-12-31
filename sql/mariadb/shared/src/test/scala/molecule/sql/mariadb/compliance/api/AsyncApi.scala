package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_async

class AsyncApi extends MUnitSuite {
  AsyncApi(this, Api_mariadb_async)
}
