package molecule.sql.h2.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.h2.setup.Api_h2_async

class Directions extends Test {
  Directions(this, Api_h2_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_h2_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_h2_async)
}
class Nested extends Test {
  Nested(this, Api_h2_async)
}
class TypesFilterAttr extends Test {
  TypesFilterAttr(this, Api_h2_async)
}
