package molecule.db.postgresql.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.postgresql.setup.Api_postgresql_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_postgresql_async)
}
