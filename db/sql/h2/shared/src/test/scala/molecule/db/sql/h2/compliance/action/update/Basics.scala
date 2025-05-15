package molecule.db.sql.h2.compliance.action.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.h2.setup.Api_h2_async

class BasicsTest extends Test {
  Basics(this, Api_h2_async)
}
