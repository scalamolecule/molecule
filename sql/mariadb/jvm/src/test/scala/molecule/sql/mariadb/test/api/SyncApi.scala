package molecule.sql.mariadb.test.api

import molecule.coreTests.test.api.SyncApi
import molecule.sql.mariadb.setup.TestSync_mariadb

object SyncApi extends SyncApi with TestSync_mariadb
