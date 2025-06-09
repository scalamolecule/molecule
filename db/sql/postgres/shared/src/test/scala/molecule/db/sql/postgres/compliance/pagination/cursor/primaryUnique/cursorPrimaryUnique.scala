package molecule.db.sql.postgres.compliance.pagination.cursor.primaryUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class DirectionsTest extends MUnit {
  Directions(this, Api_postgres_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_postgres_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_postgres_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_postgres_async)
}
class TypesFilterAttrTest extends MUnit {
  TypesFilterAttr(this, Api_postgres_async)
}
