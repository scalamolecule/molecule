package molecule.sql.sqlite.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.sqlite.setup.Test_sqlite_sync

object Test_SyncApi extends SyncApi with Test_sqlite_sync
