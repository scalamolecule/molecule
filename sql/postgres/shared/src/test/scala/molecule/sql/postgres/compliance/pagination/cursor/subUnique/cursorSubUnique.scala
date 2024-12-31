package molecule.sql.postgres.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class AttrOrder extends MUnitSuite {
  AttrOrder(this, Api_postgres_async)
}
class DirectionsStandardUnique extends MUnitSuite {
  DirectionsStandardUnique(this, Api_postgres_async)
}
class DirectionsUniqueStandard extends MUnitSuite {
  DirectionsUniqueStandard(this, Api_postgres_async)
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
class OptNested extends MUnitSuite {
  OptNested(this, Api_postgres_async)
}
class TypesUniqueValue extends MUnitSuite {
  TypesUniqueValue(this, Api_postgres_async)
}
