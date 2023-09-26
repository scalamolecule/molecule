package molecule.sql.mysql.test.api

import molecule.coreTests.test.api.SyncApi
import molecule.sql.mysql.setup.TestSync_mysql

object SyncApi extends SyncApi with TestSync_mysql
