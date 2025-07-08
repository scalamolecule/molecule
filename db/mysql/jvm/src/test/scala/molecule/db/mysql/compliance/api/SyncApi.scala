package molecule.db.mysql.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.mysql.setup.Api_mysql_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_mysql_sync)
}
