package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.SyncApi
import molecule.sql.sqlite.setup.Api_sqlite_sync

class SyncApi extends MUnitSuite {
  SyncApi(this, Api_sqlite_sync)
}
