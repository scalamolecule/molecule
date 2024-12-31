package molecule.datalog.datomic.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.datalog.datomic.setup.Api_datomic_async

class Directions extends Test {
  Directions(this, Api_datomic_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_datomic_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_datomic_async)
}
class Nested extends Test {
  Nested(this, Api_datomic_async)
}
class TypesFilterAttr extends Test {
  TypesFilterAttr(this, Api_datomic_async)
}
