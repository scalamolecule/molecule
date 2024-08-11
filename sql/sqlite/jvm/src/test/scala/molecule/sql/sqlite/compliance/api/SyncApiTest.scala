package molecule.sql.sqlite.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.sqlite.setup.TestSync_sqlite

object Test_SyncApiTest extends SyncApi with TestSync_sqlite
