package molecule.sql.postgres.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class Directions extends MUnitSuite {
  Directions(this, Api_postgres_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_postgres_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_postgres_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_postgres_async)
}
class TypesFilterAttr extends MUnitSuite {
  TypesFilterAttr(this, Api_postgres_async)
}
