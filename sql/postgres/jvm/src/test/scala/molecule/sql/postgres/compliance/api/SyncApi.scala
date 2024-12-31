package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Api_postgres_sync

class SyncApi extends Test {
  SyncApi(this, Api_postgres_sync)
}
