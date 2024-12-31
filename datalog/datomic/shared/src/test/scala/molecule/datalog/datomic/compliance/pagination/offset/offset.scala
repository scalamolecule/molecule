package molecule.datalog.datomic.compliance.pagination.offset

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.offset._
import molecule.datalog.datomic.setup.Api_datomic_async

class OffsetBackwards extends Test {
  OffsetBackwards(this, Api_datomic_async)
}
class OffsetForward extends Test {
  OffsetForward(this, Api_datomic_async)
}
class OffsetSemantics extends Test {
  OffsetSemantics(this, Api_datomic_async)
}
