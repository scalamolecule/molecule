package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.SyncApi
import molecule.sql.h2.setup.Api_h2_sync

class SyncApi extends MUnitSuite {
  SyncApi(this, Api_h2_sync)
}
