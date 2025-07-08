package molecule.db.postgres.compliance.action.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.Basics
import molecule.db.postgres.setup.Api_postgres_async

class BasicsTest extends MUnit {
  Basics(this, Api_postgres_async)
}
