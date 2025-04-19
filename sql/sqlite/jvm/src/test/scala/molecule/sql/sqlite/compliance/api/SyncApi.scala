package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api.*
import molecule.sql.sqlite.setup.Api_sqlite_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_sqlite_sync)
}
