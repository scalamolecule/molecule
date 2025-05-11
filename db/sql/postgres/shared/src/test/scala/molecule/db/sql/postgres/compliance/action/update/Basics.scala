package molecule.db.sql.postgres.compliance.action.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.Basics
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class BasicsTest extends Test {
  Basics(this, Api_postgres_async)
}
