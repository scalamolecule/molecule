package molecule.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.mariadb.setup.Api_mariadb_async

class Directions extends MUnitSuite {
  Directions(this, Api_mariadb_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_mariadb_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_mariadb_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_mariadb_async)
}
class TypesFilterAttr extends MUnitSuite {
  TypesFilterAttr(this, Api_mariadb_async)
}
