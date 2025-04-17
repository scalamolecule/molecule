package molecule.sql.mysql.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mysql.setup.Api_mysql_async

class DirectionsTest extends Test {
  Directions(this, Api_mysql_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_mysql_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_mysql_async)
}
class NestedTest extends Test {
  Nested(this, Api_mysql_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_mysql_async)
}
