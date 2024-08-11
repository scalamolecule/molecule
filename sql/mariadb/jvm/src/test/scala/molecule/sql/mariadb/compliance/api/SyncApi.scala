package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.mariadb.setup.TestSync_mariadb

object Test_SyncApi extends SyncApi with TestSync_mariadb
