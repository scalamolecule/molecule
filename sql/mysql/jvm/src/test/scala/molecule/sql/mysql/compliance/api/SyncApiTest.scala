package molecule.sql.mysql.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.mysql.setup.Test_mysql_sync

object Test_SyncApiTest extends SyncApi with Test_mysql_sync
