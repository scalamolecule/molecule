package molecule.db.sql.postgres.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql.postgres.setup.Api_postgres_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_postgres_sync)
}
