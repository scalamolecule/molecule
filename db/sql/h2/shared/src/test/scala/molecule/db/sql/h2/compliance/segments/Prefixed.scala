package molecule.db.sql.h2.compliance.segments

import molecule.core.setup.MUnit
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.h2.setup.Api_h2_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_h2_async)
}
