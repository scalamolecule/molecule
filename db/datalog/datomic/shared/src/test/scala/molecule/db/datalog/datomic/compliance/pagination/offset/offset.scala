package molecule.db.datalog.datomic.compliance.pagination.offset

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class OffsetBackwardsTest extends Test {
  OffsetBackwards(this, Api_datomic_async)
}
class OffsetForwardTest extends Test {
  OffsetForward(this, Api_datomic_async)
}
class OffsetSemanticsTest extends Test {
  OffsetSemantics(this, Api_datomic_async)
}
