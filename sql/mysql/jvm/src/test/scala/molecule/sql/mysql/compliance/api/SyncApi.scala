package molecule.sql.mysql.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.SyncApi
import molecule.sql.mysql.setup.Api_mysql_sync

class SyncApi extends MUnitSuite {
  SyncApi(this, Api_mysql_sync)
}
