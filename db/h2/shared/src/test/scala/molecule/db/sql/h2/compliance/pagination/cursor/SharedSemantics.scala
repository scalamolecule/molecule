package molecule.db.sql.h2.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.sql.h2.setup.Api_h2_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_h2_async)
}
