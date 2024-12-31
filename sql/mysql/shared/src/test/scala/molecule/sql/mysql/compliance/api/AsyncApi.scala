package molecule.sql.mysql.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.Api_mysql_async

class AsyncApi extends MUnitSuite {
  AsyncApi(this, Api_mysql_async)
}
