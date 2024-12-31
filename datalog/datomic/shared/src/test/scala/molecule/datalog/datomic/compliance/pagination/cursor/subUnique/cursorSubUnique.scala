package molecule.datalog.datomic.compliance.pagination.cursor.subUnique

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.datalog.datomic.setup.Api_datomic_async

class AttrOrder extends MUnitSuite {
  AttrOrder(this, Api_datomic_async)
}
class DirectionsStandardUnique extends MUnitSuite {
  DirectionsStandardUnique(this, Api_datomic_async)
}
class DirectionsUniqueStandard extends MUnitSuite {
  DirectionsUniqueStandard(this, Api_datomic_async)
}
class MutationAdd extends MUnitSuite {
  MutationAdd(this, Api_datomic_async)
}
class MutationDelete extends MUnitSuite {
  MutationDelete(this, Api_datomic_async)
}
class Nested extends MUnitSuite {
  Nested(this, Api_datomic_async)
}
class OptNested extends MUnitSuite {
  OptNested(this, Api_datomic_async)
}
class TypesUniqueValue extends MUnitSuite {
  TypesUniqueValue(this, Api_datomic_async)
}
