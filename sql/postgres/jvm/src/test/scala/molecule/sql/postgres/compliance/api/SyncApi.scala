package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api.SyncApi
import molecule.sql.postgres.setup.Api_postgres_sync

class SyncApi extends MUnitSuite {
  SyncApi(this, Api_postgres_sync)
}
