package molecule.datalog.datomic.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset.*
import molecule.datalog.datomic.setup.Api_datomic_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_datomic_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_datomic_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_datomic_async)
}
