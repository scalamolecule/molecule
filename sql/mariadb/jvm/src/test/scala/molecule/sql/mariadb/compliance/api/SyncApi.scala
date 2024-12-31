package molecule.sql.mariadb.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.SyncApi
import molecule.sql.mariadb.setup.Api_mariadb_sync

class SyncApi extends MUnitSuite {
  SyncApi(this, Api_mariadb_sync)
}
