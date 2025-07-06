package molecule.db.datalog.datomic.compliance.relation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.relation.DatomicNoOptionals
import molecule.db.compliance.test.relation.flat.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FlatEntityTest extends MUnit {
  FlatEntity(this, Api_datomic_async)
}
//class FlatOptEntityTest extends MUnit {
//  FlatOptEntity(this, Api_datomic_async)
//}
//class FlatOptRefTest extends MUnit {
//  FlatOptRef(this, Api_datomic_async)
//}
//class FlatOptRefAdjacentTest extends MUnit {
//  FlatOptRefAdjacent(this, Api_datomic_async)
//}
//class FlatOptRefNestedTest extends MUnit {
//  FlatOptRefNested(this, Api_datomic_async)
//}
class FlatRefTest extends MUnit {
  FlatRef(this, Api_datomic_async)
}


// Disallowance tests
class DatomicNoOptionalsTest extends MUnit {
  DatomicNoOptionals(this, Api_datomic_async)
}
