package molecule.sql.mysql.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mysql.setup.Api_mysql_async

class Directions extends Test {
  Directions(this, Api_mysql_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_mysql_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_mysql_async)
}
class Nested extends Test {
  Nested(this, Api_mysql_async)
}
class TypesFilterAttr extends Test {
  TypesFilterAttr(this, Api_mysql_async)
}
