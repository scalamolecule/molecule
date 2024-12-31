package molecule.sql.mysql.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mysql.setup.Api_mysql_async

class Directions extends MUnitSuite {
  Directions(this, Api_mysql_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_mysql_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_mysql_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_mysql_async)
}
class TypesFilterAttr extends MUnitSuite {
  TypesFilterAttr(this, Api_mysql_async)
}
