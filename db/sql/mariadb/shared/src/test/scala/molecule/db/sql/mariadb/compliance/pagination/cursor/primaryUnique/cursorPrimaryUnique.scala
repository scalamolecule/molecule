package molecule.db.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class DirectionsTest extends MUnit {
  Directions(this, Api_mariadb_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_mariadb_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_mariadb_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_mariadb_async)
}
class TypesFilterAttrTest extends MUnit {
  TypesFilterAttr(this, Api_mariadb_async)
}
