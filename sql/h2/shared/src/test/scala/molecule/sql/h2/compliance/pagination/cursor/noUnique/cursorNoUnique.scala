package molecule.sql.h2.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.h2.setup.Api_h2_async

class AttrOrderMandatory extends MUnitSuite {
  AttrOrderMandatory(this, Api_h2_async)
}
class AttrOrderOptional extends MUnitSuite {
  AttrOrderOptional(this, Api_h2_async)
}
class DirectionsMandatory extends MUnitSuite {
  DirectionsMandatory(this, Api_h2_async)
}
class DirectionsOptional extends MUnitSuite {
  DirectionsOptional(this, Api_h2_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_h2_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_h2_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_h2_async)
}
class OptNested extends MUnitSuite {
  OptNested(this, Api_h2_async)
}
class TypesOptional extends MUnitSuite {
  TypesOptional(this, Api_h2_async)
}
