package molecule.db.datalog.datomic.compliance.pagination.offset

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.offset.{OffsetBackwards, OffsetForward, OffsetSemantics}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class OffsetBackwardsTest extends MUnit {
  OffsetBackwards(this, Api_datomic_async)
}
class OffsetForwardTest extends MUnit {
  OffsetForward(this, Api_datomic_async)
}
class OffsetSemanticsTest extends MUnit {
  OffsetSemantics(this, Api_datomic_async)
}
