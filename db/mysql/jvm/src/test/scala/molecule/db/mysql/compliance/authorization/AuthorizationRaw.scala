package molecule.db.mysql.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.mysql.setup.Api_mysql_async

class AuthorizationRaw extends MUnit {
  Authorization_raw_access(this, Api_mysql_async)
}