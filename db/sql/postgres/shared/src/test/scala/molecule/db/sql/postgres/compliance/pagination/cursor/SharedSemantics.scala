package molecule.db.sql.postgres.compliance.pagination.cursor

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql.postgres.setup.Api_postgres_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_postgres_async)
}
