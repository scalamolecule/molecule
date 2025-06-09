package molecule.db.sql.h2.compliance.pagination.cursor.subUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.subUnique.*
import molecule.db.sql.h2.setup.Api_h2_async

class AttrOrderTest extends MUnit {
  AttrOrder(this, Api_h2_async)
}
class DirectionsStandardUniqueTest extends MUnit {
  DirectionsStandardUnique(this, Api_h2_async)
}
class DirectionsUniqueStandardTest extends MUnit {
  DirectionsUniqueStandard(this, Api_h2_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_h2_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_h2_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_h2_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_h2_async)
}
class TypesUniqueValueTest extends MUnit {
  TypesUniqueValue(this, Api_h2_async)
}
