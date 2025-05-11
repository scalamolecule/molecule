package molecule.db.datalog.datomic.compliance.filterAttr.seq

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filterAttr.seq.{Adjacent, CrossEntity, Types}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AdjacentTest extends Test {
  Adjacent(this, Api_datomic_async)
}
class CrossEntityTest extends Test {
  CrossEntity(this, Api_datomic_async)
}
class TypesTest extends Test {
  Types(this, Api_datomic_async)
}
