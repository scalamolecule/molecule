package molecule.datalog.datomic.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.SyncApi
import molecule.datalog.datomic.setup.Api_datomic_sync

class SyncApi extends MUnitSuite {
  SyncApi(this, Api_datomic_sync)
}
