package molecule.db.sql.mariadb.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql.mariadb.setup.Api_mariadb_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_mariadb_sync)
}
