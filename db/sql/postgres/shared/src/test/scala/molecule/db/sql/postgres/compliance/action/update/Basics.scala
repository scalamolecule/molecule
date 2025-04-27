package molecule.db.sql.postgres.compliance.action.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class BasicsTest extends Test {
  Basics(this, Api_postgres_async)
}
