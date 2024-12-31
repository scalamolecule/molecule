package molecule.sql.h2.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.h2.setup.Api_h2_async

class AttrOrder extends MUnitSuite {
  AttrOrder(this, Api_h2_async)
}
class DirectionsStandardUnique extends MUnitSuite {
  DirectionsStandardUnique(this, Api_h2_async)
}
class DirectionsUniqueStandard extends MUnitSuite {
  DirectionsUniqueStandard(this, Api_h2_async)
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
class TypesUniqueValue extends MUnitSuite {
  TypesUniqueValue(this, Api_h2_async)
}
