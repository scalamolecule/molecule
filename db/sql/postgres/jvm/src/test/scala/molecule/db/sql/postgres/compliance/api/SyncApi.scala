package molecule.db.sql.postgres.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_postgres_sync)
}
