package molecule.sql.h2.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.h2.setup.TestSync_h2

object SyncApi extends SyncApi with TestSync_h2
