package molecule.db.sql.sqlite.compliance.pagination.cursor

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_sqlite_async)
}
