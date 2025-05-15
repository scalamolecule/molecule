package molecule.db.datalog.datomic.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.SyncApi
import molecule.db.datalog.datomic.setup.Api_datomic_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_datomic_sync)
}
