package molecule.db.sql.sqlite.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_sqlite_sync)
}
