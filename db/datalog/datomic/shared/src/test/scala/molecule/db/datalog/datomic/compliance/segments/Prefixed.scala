package molecule.db.datalog.datomic.compliance.segments

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.segments.Prefixed
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class PrefixedTest extends Test {
  Prefixed(this, Api_datomic_async)
}
