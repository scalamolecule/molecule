package molecule.db.sqlite.compliance.crud.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.Basics
import molecule.db.sqlite.setup.Api_sqlite_async

class BasicsTest extends MUnit {
  Basics(this, Api_sqlite_async)
}
