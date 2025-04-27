package molecule.db.datalog.datomic.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class PrefixedTest extends Test {
  Prefixed(this, Api_datomic_async)
}
