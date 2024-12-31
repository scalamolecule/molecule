package molecule.sql.postgres.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class Directions extends Test {
  Directions(this, Api_postgres_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_postgres_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_postgres_async)
}
class Nested extends Test {
  Nested(this, Api_postgres_async)
}
class TypesFilterAttr extends Test {
  TypesFilterAttr(this, Api_postgres_async)
}
