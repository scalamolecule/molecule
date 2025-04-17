package molecule.sql.h2.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.sql.h2.setup.Api_h2_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_h2_sync)
}
