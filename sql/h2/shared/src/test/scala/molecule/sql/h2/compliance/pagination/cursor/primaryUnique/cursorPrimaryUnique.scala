package molecule.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.h2.setup.Api_h2_async

class Directions extends MUnitSuite {
  Directions(this, Api_h2_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_h2_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_h2_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_h2_async)
}
class TypesFilterAttr extends MUnitSuite {
  TypesFilterAttr(this, Api_h2_async)
}
