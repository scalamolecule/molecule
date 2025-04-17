package molecule.sql.postgres.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class AttrOrderTest extends Test {
  AttrOrder(this, Api_postgres_async)
}
class DirectionsStandardUniqueTest extends Test {
  DirectionsStandardUnique(this, Api_postgres_async)
}
class DirectionsUniqueStandardTest extends Test {
  DirectionsUniqueStandard(this, Api_postgres_async)
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
class OptNestedTest extends Test {
  OptNested(this, Api_postgres_async)
}
class TypesUniqueValueTest extends Test {
  TypesUniqueValue(this, Api_postgres_async)
}
