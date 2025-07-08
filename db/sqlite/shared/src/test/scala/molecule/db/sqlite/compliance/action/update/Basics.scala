package molecule.db.sqlite.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sqlite.setup.Api_sqlite_async

class BasicsTest extends MUnit {
  Basics(this, Api_sqlite_async)
}
