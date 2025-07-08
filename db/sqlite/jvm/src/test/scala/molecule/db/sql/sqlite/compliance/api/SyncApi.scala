package molecule.db.sql.sqlite.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql.sqlite.setup.Api_sqlite_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_sqlite_sync)
}
