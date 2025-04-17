package molecule.datalog.datomic.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.datalog.datomic.setup.Api_datomic_async

class DirectionsTest extends Test {
  Directions(this, Api_datomic_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_datomic_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_datomic_async)
}
class NestedTest extends Test {
  Nested(this, Api_datomic_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_datomic_async)
}
