package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.mariadb.setup.Test_mariadb_sync

object Test_SyncApi extends SyncApi with Test_mariadb_sync
