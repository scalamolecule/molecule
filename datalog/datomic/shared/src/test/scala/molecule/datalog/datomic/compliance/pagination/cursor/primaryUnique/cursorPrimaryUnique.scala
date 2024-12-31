package molecule.datalog.datomic.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.datalog.datomic.setup.Api_datomic_async

class Directions extends MUnitSuite {
  Directions(this, Api_datomic_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_datomic_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_datomic_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_datomic_async)
}
class TypesFilterAttr extends MUnitSuite {
  TypesFilterAttr(this, Api_datomic_async)
}
