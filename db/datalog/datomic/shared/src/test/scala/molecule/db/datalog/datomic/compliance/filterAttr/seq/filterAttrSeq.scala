package molecule.db.datalog.datomic.compliance.filterAttr.seq

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AdjacentTest extends MUnit {
  Adjacent(this, Api_datomic_async)
}
class CrossEntityTest extends MUnit {
  CrossEntity(this, Api_datomic_async)
}
class TypesTest extends MUnit {
  Types(this, Api_datomic_async)
}
