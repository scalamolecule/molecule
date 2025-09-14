package molecule.db.h2.compliance.crud.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.Basics
import molecule.db.h2.setup.Api_h2_async

class BasicsTest extends MUnit {
  Basics(this, Api_h2_async)
}
