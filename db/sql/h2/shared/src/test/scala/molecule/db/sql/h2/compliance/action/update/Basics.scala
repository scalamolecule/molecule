package molecule.db.sql.h2.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.h2.setup.Api_h2_async

class BasicsTest extends MUnit {
  Basics(this, Api_h2_async)
}
