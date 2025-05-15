package molecule.db.sql.mysql.compliance.pagination.cursor.primaryUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.cursor.primaryUnique.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class DirectionsTest extends Test {
  Directions(this, Api_mysql_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_mysql_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_mysql_async)
}
class NestedTest extends Test {
  Nested(this, Api_mysql_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_mysql_async)
}
