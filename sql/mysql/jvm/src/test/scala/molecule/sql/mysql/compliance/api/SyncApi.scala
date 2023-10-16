package molecule.sql.mysql.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.sql.mysql.setup.TestSync_mysql

object SyncApi extends SyncApi with TestSync_mysql
