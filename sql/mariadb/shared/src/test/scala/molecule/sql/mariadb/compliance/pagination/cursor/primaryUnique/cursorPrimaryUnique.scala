package molecule.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mariadb.setup.Api_mariadb_async

class DirectionsTest extends Test {
  Directions(this, Api_mariadb_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_mariadb_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_mariadb_async)
}
class NestedTest extends Test {
  Nested(this, Api_mariadb_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_mariadb_async)
}
