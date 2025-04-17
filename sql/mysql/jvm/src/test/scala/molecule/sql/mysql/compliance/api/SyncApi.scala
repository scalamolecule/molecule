package molecule.sql.mysql.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.Api_mysql_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_mysql_sync)
}
