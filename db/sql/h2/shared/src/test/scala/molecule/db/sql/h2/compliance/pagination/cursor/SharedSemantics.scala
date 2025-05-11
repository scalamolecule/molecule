package molecule.db.sql.h2.compliance.pagination.cursor

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class SharedSemanticsTest extends Test {
  SharedSemantics(this, Api_h2_async)
}
