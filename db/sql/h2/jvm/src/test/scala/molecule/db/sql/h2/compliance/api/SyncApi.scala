package molecule.db.sql.h2.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_h2_sync)
}
