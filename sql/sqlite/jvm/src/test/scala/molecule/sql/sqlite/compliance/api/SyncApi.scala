package molecule.sql.sqlite.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.sqlite.setup.Api_sqlite_sync

class SyncApi extends Test {
  SyncApi(this, Api_sqlite_sync)
}
