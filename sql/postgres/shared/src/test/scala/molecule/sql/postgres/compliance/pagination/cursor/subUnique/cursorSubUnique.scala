package molecule.sql.postgres.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class AttrOrder extends Test {
  AttrOrder(this, Api_postgres_async)
}
class DirectionsStandardUnique extends Test {
  DirectionsStandardUnique(this, Api_postgres_async)
}
class DirectionsUniqueStandard extends Test {
  DirectionsUniqueStandard(this, Api_postgres_async)
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
class OptNested extends Test {
  OptNested(this, Api_postgres_async)
}
class TypesUniqueValue extends Test {
  TypesUniqueValue(this, Api_postgres_async)
}
