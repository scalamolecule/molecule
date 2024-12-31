package molecule.sql.postgres.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.postgres.setup.Api_postgres_async

class AttrOrderMandatory extends Test {
  AttrOrderMandatory(this, Api_postgres_async)
}
class AttrOrderOptional extends Test {
  AttrOrderOptional(this, Api_postgres_async)
}
class DirectionsMandatory extends Test {
  DirectionsMandatory(this, Api_postgres_async)
}
class DirectionsOptional extends Test {
  DirectionsOptional(this, Api_postgres_async)
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
class TypesOptional extends Test {
  TypesOptional(this, Api_postgres_async)
}
