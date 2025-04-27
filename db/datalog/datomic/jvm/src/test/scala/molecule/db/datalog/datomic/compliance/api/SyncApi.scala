package molecule.db.datalog.datomic.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_datomic_sync)
}
