package molecule.db.sql.postgres.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql.postgres.setup.Api_postgres_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_postgres_sync)
}
