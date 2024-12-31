package molecule.sql.mysql.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.Api_mysql_async

class AsyncApi extends Test {
  AsyncApi(this, Api_mysql_async)
}
