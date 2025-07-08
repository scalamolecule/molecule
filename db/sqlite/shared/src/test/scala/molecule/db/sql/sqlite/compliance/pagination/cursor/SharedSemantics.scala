package molecule.db.sql.sqlite.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_sqlite_async)
}
