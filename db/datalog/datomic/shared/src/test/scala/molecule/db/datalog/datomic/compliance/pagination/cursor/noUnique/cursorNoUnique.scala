package molecule.db.datalog.datomic.compliance.pagination.cursor.noUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.cursor.noUnique.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AttrOrderMandatoryTest extends Test {
  AttrOrderMandatory(this, Api_datomic_async)
}
class AttrOrderOptionalTest extends Test {
  AttrOrderOptional(this, Api_datomic_async)
}
class DirectionsMandatoryTest extends Test {
  DirectionsMandatory(this, Api_datomic_async)
}
class DirectionsOptionalTest extends Test {
  DirectionsOptional(this, Api_datomic_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_datomic_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_datomic_async)
}
class NestedTest extends Test {
  Nested(this, Api_datomic_async)
}
class OptNestedTest extends Test {
  OptNested(this, Api_datomic_async)
}
class TypesOptionalTest extends Test {
  TypesOptional(this, Api_datomic_async)
}
