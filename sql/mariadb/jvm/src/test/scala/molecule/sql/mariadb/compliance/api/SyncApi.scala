package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_mariadb_sync)
}
