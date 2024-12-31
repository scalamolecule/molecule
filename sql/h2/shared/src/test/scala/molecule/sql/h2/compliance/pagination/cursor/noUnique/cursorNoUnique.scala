package molecule.sql.h2.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.h2.setup.Api_h2_async

class AttrOrderMandatory extends Test {
  AttrOrderMandatory(this, Api_h2_async)
}
class AttrOrderOptional extends Test {
  AttrOrderOptional(this, Api_h2_async)
}
class DirectionsMandatory extends Test {
  DirectionsMandatory(this, Api_h2_async)
}
class DirectionsOptional extends Test {
  DirectionsOptional(this, Api_h2_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_h2_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_h2_async)
}
class Nested extends Test {
  Nested(this, Api_h2_async)
}
class OptNested extends Test {
  OptNested(this, Api_h2_async)
}
class TypesOptional extends Test {
  TypesOptional(this, Api_h2_async)
}
