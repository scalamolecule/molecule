package molecule.datalog.datomic.compliance.segments

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.segments._
import molecule.datalog.datomic.setup.Api_datomic_async

class PrefixedTest extends Test {
  Prefixed(this, Api_datomic_async)
}
