package molecule.sql.postgres.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class AttrOrderMandatory extends MUnitSuite {
  AttrOrderMandatory(this, Api_postgres_async)
}
class AttrOrderOptional extends MUnitSuite {
  AttrOrderOptional(this, Api_postgres_async)
}
class DirectionsMandatory extends MUnitSuite {
  DirectionsMandatory(this, Api_postgres_async)
}
class DirectionsOptional extends MUnitSuite {
  DirectionsOptional(this, Api_postgres_async)
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
class TypesOptional extends MUnitSuite {
  TypesOptional(this, Api_postgres_async)
}
