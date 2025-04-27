package molecule.db.sql.mysql.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class AsyncApiTest extends Test {
  AsyncApi(this, Api_mysql_async)
}
