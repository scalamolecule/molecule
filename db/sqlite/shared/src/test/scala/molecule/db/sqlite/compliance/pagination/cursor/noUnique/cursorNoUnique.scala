package molecule.db.sqlite.compliance.pagination.cursor.noUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.cursor.noUnique.*
import molecule.db.sqlite.setup.Api_sqlite_async

class AttrOrderMandatoryTest extends MUnit {
  AttrOrderMandatory(this, Api_sqlite_async)
}
class AttrOrderOptionalTest extends MUnit {
  AttrOrderOptional(this, Api_sqlite_async)
}
class DirectionsMandatoryTest extends MUnit {
  DirectionsMandatory(this, Api_sqlite_async)
}
class DirectionsOptionalTest extends MUnit {
  DirectionsOptional(this, Api_sqlite_async)
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
class TypesOptionalTest extends MUnit {
  TypesOptional(this, Api_sqlite_async)
}
