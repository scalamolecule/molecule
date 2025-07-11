package molecule.db.postgresql.compliance.pagination.cursor.subUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.subUnique.*
import molecule.db.postgresql.setup.Api_postgresql_async

class AttrOrderTest extends MUnit {
  AttrOrder(this, Api_postgresql_async)
}
class DirectionsStandardUniqueTest extends MUnit {
  DirectionsStandardUnique(this, Api_postgresql_async)
}
class DirectionsUniqueStandardTest extends MUnit {
  DirectionsUniqueStandard(this, Api_postgresql_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_postgresql_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_postgresql_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_postgresql_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_postgresql_async)
}
class TypesUniqueValueTest extends MUnit {
  TypesUniqueValue(this, Api_postgresql_async)
}
