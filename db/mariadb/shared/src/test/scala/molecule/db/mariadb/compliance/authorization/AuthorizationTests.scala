package molecule.db.mariadb.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.mariadb.setup.Api_mariadb_async

class Authorization1 extends MUnit {
  Authorization1_roles(this, Api_mariadb_async)
}
class Authorization2 extends MUnit {
  Authorization2_roleActions(this, Api_mariadb_async)
}
class Authorization3 extends MUnit {
  Authorization3_attrRoles(this, Api_mariadb_async)
}
class Authorization4 extends MUnit {
  Authorization4_attrUpdate(this, Api_mariadb_async)
}

class AuthorizationOverview extends MUnit {
  Authorization_overview(this, Api_mariadb_async)
}

