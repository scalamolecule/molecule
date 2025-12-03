package molecule.db.h2.compliance.authorization

import molecule.core.setup.MUnit
import molecule.db.compliance.test.authorization.*
import molecule.db.h2.setup.Api_h2_async

class Authorization0 extends MUnit {
  Authorization0_overview(this, Api_h2_async)
}
class Authorization1 extends MUnit {
  Authorization1_roles(this, Api_h2_async)
}
class Authorization2 extends MUnit {
  Authorization2_roleActions(this, Api_h2_async)
}
class Authorization3 extends MUnit {
  Authorization3_attrRoles(this, Api_h2_async)
}
class Authorization4 extends MUnit {
  Authorization4_attrUpdate(this, Api_h2_async)
}

