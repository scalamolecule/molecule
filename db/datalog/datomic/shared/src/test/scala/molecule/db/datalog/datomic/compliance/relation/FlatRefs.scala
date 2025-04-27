package molecule.db.datalog.datomic.compliance.relation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.relation.flat.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class FlatRefTest extends Test {
  FlatRef(this, Api_datomic_async)
}
class FlatOptEntityTest extends Test {
  FlatOptEntity(this, Api_datomic_async)
}
class FlatOptRefTest extends Test {
  FlatOptRef(this, Api_datomic_async)
}
class FlatOptRefNestedTest extends Test {
  FlatOptRefNested(this, Api_datomic_async)
}
class FlatOptRefAdjacentTest extends Test {
  FlatOptRefAdjacent(this, Api_datomic_async)
}
