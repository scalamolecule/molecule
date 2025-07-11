package molecule.db.postgresql.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.postgresql.setup.Api_postgresql_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_postgresql_sync)
}
