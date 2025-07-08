package molecule.db.mysql.compliance.pagination.cursor.primaryUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.mysql.setup.Api_mysql_async

class DirectionsTest extends MUnit {
  Directions(this, Api_mysql_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_mysql_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_mysql_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_mysql_async)
}
class TypesFilterAttrTest extends MUnit {
  TypesFilterAttr(this, Api_mysql_async)
}
