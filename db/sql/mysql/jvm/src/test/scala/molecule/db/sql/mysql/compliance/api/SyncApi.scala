package molecule.db.sql.mysql.compliance.api

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.api.SyncApi
import molecule.db.sql.mysql.setup.Api_mysql_sync

class SyncApiTest extends Test {
  SyncApi(this, Api_mysql_sync)
}
