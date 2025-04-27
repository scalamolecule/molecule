package molecule.sql.postgres.compliance.pagination.cursor.primaryUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.primaryUnique.*
import molecule.sql.postgres.setup.Api_postgres_async

class DirectionsTest extends Test {
  Directions(this, Api_postgres_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_postgres_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_postgres_async)
}
class NestedTest extends Test {
  Nested(this, Api_postgres_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_postgres_async)
}
