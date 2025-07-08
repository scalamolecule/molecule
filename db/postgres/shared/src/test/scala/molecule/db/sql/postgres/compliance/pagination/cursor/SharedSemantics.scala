package molecule.db.sql.postgres.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql.postgres.setup.Api_postgres_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_postgres_async)
}
