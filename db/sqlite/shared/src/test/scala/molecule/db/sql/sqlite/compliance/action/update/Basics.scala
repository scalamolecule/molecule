package molecule.db.sql.sqlite.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class BasicsTest extends MUnit {
  Basics(this, Api_sqlite_async)
}
