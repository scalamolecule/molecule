package molecule.db.datalog.datomic.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.datalog.datomic.setup.Api_datomic_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_datomic_sync)
}
