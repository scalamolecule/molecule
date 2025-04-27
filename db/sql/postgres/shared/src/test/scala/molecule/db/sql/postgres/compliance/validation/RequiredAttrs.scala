package molecule.db.sql.postgres.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_postgres_async)
}
