package molecule.datalog.datomic.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.datalog.datomic.setup.Api_datomic_async

class AttrOrderMandatory extends Test {
  AttrOrderMandatory(this, Api_datomic_async)
}
class AttrOrderOptional extends Test {
  AttrOrderOptional(this, Api_datomic_async)
}
class DirectionsMandatory extends Test {
  DirectionsMandatory(this, Api_datomic_async)
}
class DirectionsOptional extends Test {
  DirectionsOptional(this, Api_datomic_async)
}
class MutationAdd extends Test {
  MutationAdd(this, Api_datomic_async)
}
class MutationDelete extends Test {
  MutationDelete(this, Api_datomic_async)
}
class Nested extends Test {
  Nested(this, Api_datomic_async)
}
class OptNested extends Test {
  OptNested(this, Api_datomic_async)
}
class TypesOptional extends Test {
  TypesOptional(this, Api_datomic_async)
}
