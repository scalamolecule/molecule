package molecule.db.datalog.datomic.compliance.pagination.cursor.noUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.cursor.noUnique.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AttrOrderMandatoryTest extends MUnit {
  AttrOrderMandatory(this, Api_datomic_async)
}
class AttrOrderOptionalTest extends MUnit {
  AttrOrderOptional(this, Api_datomic_async)
}
class DirectionsMandatoryTest extends MUnit {
  DirectionsMandatory(this, Api_datomic_async)
}
class DirectionsOptionalTest extends MUnit {
  DirectionsOptional(this, Api_datomic_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_datomic_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_datomic_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_datomic_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_datomic_async)
}
class TypesOptionalTest extends MUnit {
  TypesOptional(this, Api_datomic_async)
}
