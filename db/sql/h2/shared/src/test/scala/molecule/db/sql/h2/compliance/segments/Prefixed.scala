package molecule.db.sql.h2.compliance.segments

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.sql.h2.setup.Api_h2_async

class PrefixedTest extends Test {
  Prefixed(this, Api_h2_async)
}
