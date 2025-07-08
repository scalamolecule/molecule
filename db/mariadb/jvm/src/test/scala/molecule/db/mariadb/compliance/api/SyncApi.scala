package molecule.db.mariadb.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.mariadb.setup.Api_mariadb_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_mariadb_sync)
}
