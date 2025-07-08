package molecule.db.postgres.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.postgres.setup.Api_postgres_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_postgres_async)
}
