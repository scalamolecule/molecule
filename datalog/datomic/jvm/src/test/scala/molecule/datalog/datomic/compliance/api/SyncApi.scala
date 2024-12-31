package molecule.datalog.datomic.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.Api_datomic_sync

class SyncApi extends Test {
  SyncApi(this, Api_datomic_sync)
}
