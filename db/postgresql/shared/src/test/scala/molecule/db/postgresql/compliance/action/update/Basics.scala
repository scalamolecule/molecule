package molecule.db.postgresql.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.Basics
import molecule.db.postgresql.setup.Api_postgresql_async

class BasicsTest extends MUnit {
  Basics(this, Api_postgresql_async)
}
