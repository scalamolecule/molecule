package molecule.db.sqlite.compliance.pagination.cursor.subUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.subUnique.*
import molecule.db.sqlite.setup.Api_sqlite_async

class AttrOrderTest extends MUnit {
  AttrOrder(this, Api_sqlite_async)
}
class DirectionsStandardUniqueTest extends MUnit {
  DirectionsStandardUnique(this, Api_sqlite_async)
}
class DirectionsUniqueStandardTest extends MUnit {
  DirectionsUniqueStandard(this, Api_sqlite_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_sqlite_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_sqlite_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_sqlite_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_sqlite_async)
}
class TypesUniqueValueTest extends MUnit {
  TypesUniqueValue(this, Api_sqlite_async)
}
