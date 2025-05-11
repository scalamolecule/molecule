package molecule.db.sql.sqlite.compliance.action.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class BasicsTest extends Test {
  Basics(this, Api_sqlite_async)
}
