package molecule.db.sqlite.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sqlite.setup.Api_sqlite_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_sqlite_async)
}
