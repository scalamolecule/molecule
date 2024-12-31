package molecule.datalog.datomic.compliance.pagination.offset

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.offset._
import molecule.datalog.datomic.setup.Api_datomic_async

class OffsetBackwards extends MUnitSuite {
  OffsetBackwards(this, Api_datomic_async)
}
class OffsetForward extends MUnitSuite {
  OffsetForward(this, Api_datomic_async)
}
class OffsetSemantics extends MUnitSuite {
  OffsetSemantics(this, Api_datomic_async)
}
