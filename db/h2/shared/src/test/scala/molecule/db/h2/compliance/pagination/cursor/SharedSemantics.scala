package molecule.db.h2.compliance.pagination.cursor

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.SharedSemantics
import molecule.db.h2.setup.Api_h2_async

class SharedSemanticsTest extends MUnit {
  SharedSemantics(this, Api_h2_async)
}
