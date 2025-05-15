package molecule.db.datalog.datomic.compliance.pagination.cursor.primaryUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

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
