package molecule.db.h2.compliance.segments

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.h2.setup.Api_h2_async

class PrefixedTest extends MUnit {
  Prefixed(this, Api_h2_async)
}
